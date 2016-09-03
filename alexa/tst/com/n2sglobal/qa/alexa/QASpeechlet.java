package com.n2sglobal.qa.alexa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	private Topic[] topics;
	@Override
	public SpeechletResponse onIntent(IntentRequest arg0, Session arg1)
			throws SpeechletException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSessionStarted(SessionStartedRequest arg0, Session arg1)
			throws SpeechletException {
		log.info("onSessionStarted requestId={}, sessionId={}",
				arg0.getRequestId(), arg1.getSessionId());
		String text = webserviceCall("topic");
		ObjectMapper mapper = new ObjectMapper();
		try {
			topics = mapper.readValue(text, Topic[].class);
			log.info("webservice call successfull ",
					topics.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private SpeechletResponse getWelcomeResponse() {
		if(topics.length==0) return null;
		StringBuffer sb = new StringBuffer();
		for(Topic topic:topics)
		{
			sb.append(topic.getTopic());
			sb.append(" ");
		}
		String speechOutput = Constants.SELECT_CATEGORY + sb.toString();
		String repromptText = speechOutput;
		return newAskResponse(speechOutput, false, repromptText, false);
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

	private SpeechletResponse newAskResponse(String stringOutput,
			boolean isOutputSsml, String repromptText, boolean isRepromptSsml) {
		OutputSpeech outputSpeech, repromptOutputSpeech;
		if (isOutputSsml) {
			outputSpeech = new SsmlOutputSpeech();
			((SsmlOutputSpeech) outputSpeech).setSsml(stringOutput);
		} else {
			outputSpeech = new PlainTextOutputSpeech();
			((PlainTextOutputSpeech) outputSpeech).setText(stringOutput);
		}

		if (isRepromptSsml) {
			repromptOutputSpeech = new SsmlOutputSpeech();
			((SsmlOutputSpeech) repromptOutputSpeech).setSsml(repromptText);
		} else {
			repromptOutputSpeech = new PlainTextOutputSpeech();
			((PlainTextOutputSpeech) repromptOutputSpeech)
					.setText(repromptText);
		}
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(repromptOutputSpeech);
		return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
	}
}
