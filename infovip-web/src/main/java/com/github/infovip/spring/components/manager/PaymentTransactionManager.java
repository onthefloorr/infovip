package com.github.infovip.spring.components.manager;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;

import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;


/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class PaymentTransactionManager {

	
	@Autowired
	private ElasticsearchRestTemplate esTemplate;

	private Client esClient;
	
	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;

	
	public PaymentTransactionManager() {}
	
	
	@PostConstruct
	public void postConstruct() {
	}

}
