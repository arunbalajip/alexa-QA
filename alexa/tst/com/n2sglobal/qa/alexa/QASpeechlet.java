package com.n2sglobal.qa.alexa;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
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
	@Override
	public SpeechletResponse onIntent(IntentRequest arg0, Session arg1)
			throws SpeechletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest arg0, Session arg1)
			throws SpeechletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onSessionEnded(SessionEndedRequest arg0, Session arg1)
			throws SpeechletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSessionStarted(SessionStartedRequest arg0, Session arg1)
			throws SpeechletException {
		// TODO Auto-generated method stub

	}

}
