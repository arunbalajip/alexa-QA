package com.n2sglobal.qa.alexa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.n2sglobal.qa.dto.Question;
import com.n2sglobal.qa.dto.Topic;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QASpeechlet implements Speechlet {
	private static final Logger log = LoggerFactory
			.getLogger(QASpeechlet.class);
	private static final String URL_PREFIX = "http://n2sglobal.vc7nqzja6p.us-west-2.elasticbeanstalk.com/api/";
	private static final String NOOFQUESTIONS = "noques";
	private static final String QUESTION_INDEX = "qindex";
	private static final String SCORE = "score";
	private static final String TOPIC = "topic";
	private static final String TOPICOBJ = "topicobj";

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session)
			throws SpeechletException {
		log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
				session.getSessionId());
		Intent intent = request.getIntent();
		String intentName = intent.getName();

		if ("SetCategoryIntent".equals(intentName)) {
			return SetCategoryIntent(intent, session);
		} else if ("SetNoOfQuestionsIntent".equals(intentName)) {
			return SetNoOfQuestionsIntent(intent, session);
		} else if ("MySolutionIntent".equals(intentName)) {
			return MySolutionIntent(intent, session);
		} else if ("AMAZON.HelpIntent".equals(intentName)) {
			return getWelcomeResponse();
		} else if ("AMAZON.StopIntent".equals(intentName)) {
			return Tell(Constants.GOODBYE);
		} else if ("AMAZON.CancelIntent".equals(intentName)) {
			return Tell(Constants.GOODBYE);
		} else {
			throw new SpeechletException("Invalid Intent");
		}
	}

	private SpeechletResponse MySolutionIntent(Intent intent, Session session) {
		if (!session.getAttributes().containsKey(TOPICOBJ)||!session.getAttributes().containsKey(TOPIC)) {
			return getWelcomeResponse();
		}else if(!session.getAttributes().containsKey(NOOFQUESTIONS)){
			String param = Constants.WELCOME_TO + session.getAttribute(TOPIC) + "."
					+ Constants.QUESTION_NO;
			return Ask(param, Constants.QUESTION_NO);
		}
		

		Topic topic = (Topic) session.getAttribute(TOPICOBJ);
		int index = (int) session.getAttribute(QUESTION_INDEX);
		int score = (int) session.getAttribute(SCORE);
		StringBuffer sb = new StringBuffer();
		if (intent.getSlot("soln").getValue()
				.equalsIgnoreCase(topic.getQuestions().get(index).getAnswer())) {
			sb.append(Constants.CORRECT);
			score++;
		} else {
			sb.append(Constants.WRONG);
		}
		sb.append(" ");
		sb.append(Constants.SCORE_SHEET);
		sb.append(" ");
		sb.append(score);
		sb.append(" ");
		if (index >= (int) session.getAttribute(NOOFQUESTIONS)) {
			sb.append(Constants.GOODBYE);
			Tell(sb.toString());
		}
		index++;
		String question = getQuestion(topic, index);
		session.setAttribute(QUESTION_INDEX, index);
		session.setAttribute(SCORE, score);
		return Ask(sb.toString() + question, question);

	}

	private SpeechletResponse SetNoOfQuestionsIntent(Intent intent,
			Session session) {
		if (!session.getAttributes().containsKey(TOPICOBJ))
			return getWelcomeResponse();
		Slot noofquestion = intent.getSlot("ques_count");
		if (Integer.parseInt(noofquestion.getValue()) < 0)
			return Tell(Constants.INVALID);

		session.setAttribute(NOOFQUESTIONS, noofquestion.getValue());
		session.setAttribute(QUESTION_INDEX, 0);
		session.setAttribute(SCORE, 0);
		Topic topic = (Topic) session.getAttribute(TOPICOBJ);

		if (Integer.parseInt(noofquestion.getValue()) > topic.getQuestions()
				.size()){
			session.setAttribute(NOOFQUESTIONS, topic.getQuestions().size());
		}
		String question = getQuestion(topic, 0);
		String outputSpeech = Constants.STARTQUIZ + question;
		return Ask(outputSpeech, question);
	}

	private String getQuestion(Topic topic, int index) {
		Question question = topic.getQuestions().get(0);
		StringBuffer sb = new StringBuffer();
		if (question != null) {
			sb.append(" ");
			sb.append(question.getQuestion());
			sb.append(" ");
			sb.append(question.getOption1());
			sb.append(" ");
			sb.append(question.getOption2());
			sb.append(" ");
			sb.append(question.getOption3());
			sb.append(" ");
		}
		return sb.toString();
	}

	private SpeechletResponse SetCategoryIntent(Intent intent, Session session) {
		Topic[] topics = getTopics();
		Slot topicSlot = intent.getSlot("topic");
		session.setAttribute(TOPIC, topicSlot.getValue());
		for (Topic topic : topics) {
			if (topic.getTopic().equalsIgnoreCase(topicSlot.getValue())) {
				session.setAttribute(TOPICOBJ, topic);
			}
		}
		String param = Constants.WELCOME_TO + topicSlot.getValue() + "."
				+ Constants.QUESTION_NO;
		return Ask(param, Constants.QUESTION_NO);
	}

	private SpeechletResponse Tell(String message) {
		return getSpeechletResponse(message, message, false);
	}

	private SpeechletResponse Ask(String message, String repromptText) {
		return getSpeechletResponse(message, repromptText, true);
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session)
			throws SpeechletException {
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
				session.getSessionId());
		return getWelcomeResponse();
	}

	@Override
	public void onSessionEnded(SessionEndedRequest arg0, Session arg1)
			throws SpeechletException {
		log.info("onSessionEnded requestId={}, sessionId={}",
				arg0.getRequestId(), arg1.getSessionId());

	}

	@Override
	public void onSessionStarted(SessionStartedRequest arg0, Session arg1)
			throws SpeechletException {
		log.info("onSessionStarted requestId={}, sessionId={}",
				arg0.getRequestId(), arg1.getSessionId());

	}

	private Topic[] getTopics() {
		Topic[] topics = null;
		String text = webserviceCall("topic");
		ObjectMapper mapper = new ObjectMapper();
		try {
			topics = mapper.readValue(text, Topic[].class);
			log.info("webservice call successfull ", topics.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topics;
	}

	private SpeechletResponse getWelcomeResponse() {

		StringBuffer sb = new StringBuffer();
		for (Topic topic : getTopics()) {
			sb.append(topic.getTopic());
			sb.append(" ");
		}

		String speechOutput = Constants.SELECT_CATEGORY + sb.toString();
		return Ask(speechOutput, speechOutput);
	}

	private String webserviceCall(String controller) {
		InputStreamReader inputStream = null;
		BufferedReader bufferedReader = null;
		String text = "";
		try {
			String line;
			URL url = new URL(URL_PREFIX + controller);
			inputStream = new InputStreamReader(url.openStream(),
					Charset.forName("US-ASCII"));
			bufferedReader = new BufferedReader(inputStream);
			StringBuilder builder = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}
			text = builder.toString();
		} catch (IOException e) {
			// reset text variable to a blank string
			text = "";
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(bufferedReader);
		}
		return text;
	}

	private SpeechletResponse getSpeechletResponse(String speechText,
			String repromptText, boolean isAskResponse) {
		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("Q&A");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		if (isAskResponse) {
			// Create reprompt
			PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
			repromptSpeech.setText(repromptText);
			Reprompt reprompt = new Reprompt();
			reprompt.setOutputSpeech(repromptSpeech);

			return SpeechletResponse.newAskResponse(speech, reprompt, card);

		} else {
			return SpeechletResponse.newTellResponse(speech, card);
		}
	}
}
