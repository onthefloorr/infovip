package com.github.infovip.core.statistics;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.IVIP_STAT_INDEX;
import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.IVIP_STAT_TYPE;

import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

/**
 * The {@link StatisticalEventData} represents the data that will be stored as event.
 * There may be different types of event that determines the information that the event occurs.
 * 
 * @author attila
 *
 */
public class StatisticalEventData<T extends DefaultStatisticalEvent<?>> implements ESExtendedDataElement<T> , GeneralEvent  {

	public String documentID;

	private ESOperationType defaultOperationType;

	private T data;
	
	public StatisticalEventData() {
		this.defaultOperationType = ESOperationType.INDEX;
	}
	
	public StatisticalEventData(T data) {
		this();
		this.data = data;
	}

	@Override
	public void setParent(String sessionId) {
		data.setSessionId(sessionId);
	}
	
	@Override
	public String id() {
		return documentID;
	}

	@Override
	public String index() {
		return IVIP_STAT_INDEX;
	}

	@Override
	public String type() {
		return IVIP_STAT_TYPE;
	}

	@Override
	public ESOperationType operation() {
		return defaultOperationType;
	}

	
	public void setDefaultOperationType(ESOperationType defaultOperationType) {
		this.defaultOperationType = defaultOperationType;
	}
	
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	
	@Override
	public T data() {
		return data;
	}

}
