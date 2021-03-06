package com.github.infovip.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.config.deprecated.TemporaryConfig;
import com.github.infovip.core.config.deprecated.TemporaryConfigReader;

/**
 *
 * @author attila
 */
public class Configuration extends DefaultWebAppConfiguration {

    /**
     * Identifier of the default SQL connection It is used by the basic SQL
     * manager component
     */
    public static final String BEAN_SQL_ID = "DefaultJSQLConnection";

    /**
     * Identifier of the default JSP module manager component
     */
    public static final String BEAN_MODULE_ID = "JSPModuleManager";

    /**
     * Location of the modules directory
     */
    public static final String MODULE_DIRECTORY = "/modules";

    /**
     * Location of the web directory
     */
    public static final String WEB_DIRECTORY = "/WEB-INF/web";

    /**
     * Location of the resources directory
     */
    public static final String RESOURCES_DIRECTORY = "/resources";
    
    /**
     * Location of the lib directory
     */
    public static final String LIB_DIRECTORY = "/lib";

    /**
     * Location of the lib directory
     */
    public static final String WEBPACK_DIRECTORY = "/resources/webpack/dist";

    /**
     * Location of the modules directory
     */
    public static final String DEFAULT_MODULE_DIRECTORY = "/WEB-INF/web/modules";
    
    /**
     * Location of the test directory
     */
    public static final String DEFAULT_TEST_DIRECTORY = "/WEB-INF/web/test";

    /**
     * Identifier of the application's context path
     */
    public static final String CONTEXT_PATH_ID = "contextPath";

    /**
     * Identifier of the resources
     */
    public static final String RESOURCES_ID = "resources";

    /**
     * Container identifier
     */
    public static final String CONTAINER_ID = "container";

    /**
     * Title of the page
     */
    public static String PAGE_TITLE = "Infovip";

    /**
     * Application's context name
     */
    public static final String APPLICATION_CONTEXT_NAME = "";

    /**
     * Defines the name of the application
     */
    public static final String APP_NAME = "infovip-ear-1.0-SNAPSHOT";

    /**
     * Defines the name of the main EJB module
     */
    public static final String EJB_MODULE_NAME = "infovip-ejb-1.0-SNAPSHOT";

    /**
     * Name of the default elasticsearch template
     */
    public static final String ELASTICSEARCH_TEMPLATE_NAME = "elasticsearchTemplate";
    
    /**
     * Name of the default Client
     */
    public static final String ELASTICSEARCH_CLIENT_ID = "esClient";

    /**
     * Identifier of the default client settings object
     */
    public static final String ELASTICSEARCH_CLIENT_SETTINGS = "esClientSettings";
   
    /**
     * Location of the tiles
     */
    public static final String TILES = "/WEB-INF/tiles/";
    
    

    /**
     * Creates the jndi string
     *
     * @param mappendName The component's mappedName
     *
     * @return Returns with the generated jndi string
     */
    public static final String jndiLookupName(String mappedName) {
        return String.format("java:global/%s/%s/%s", APP_NAME, EJB_MODULE_NAME, mappedName);
    }

    /**
     * Represents the session value as a string
     *
     * @param s
     * @return
     */
    public static final String sessionValue(BaseSessionInformation s) {
        return s.toString();
    }

    /**
     * Container id
     */
    public static final String BEAN_CONTAINER_ID = "container";

    public String getBEAN_SQL_ID() {
        return BEAN_SQL_ID;
    }

    public String getBEAN_MODULE_ID() {
        return BEAN_MODULE_ID;
    }

    public String getMODULE_DIRECTORY() {
        return MODULE_DIRECTORY;
    }

    public String getDEFAULT_MODULE_DIRECTORY() {
        return DEFAULT_MODULE_DIRECTORY;
    }

    public String getCONTEXT_PATH_ID() {
        return CONTEXT_PATH_ID;
    }

    public String getPAGE_TITLE() {
        return PAGE_TITLE;
    }

    public void setPAGE_TITLE(String title) {
        PAGE_TITLE = title;
    }

    public String getCONTAINER_ID() {
        return CONTAINER_ID;
    }

    
    public String getTILES() {
    	return TILES;
    }
    
    public String getWEB_DIRECTORY() {
        return WEB_DIRECTORY;
    }


    private TemporaryConfig temporaryConfig;
    
    private Logger logger = org.apache.log4j.Logger.getLogger(Configuration.class);

    
    public Configuration() {
    	try {
			this.temporaryConfig = TemporaryConfigReader.readTemporaryConfiguration( Configuration.class.getClassLoader().getResourceAsStream("config/temporary.yaml") );
			BLOG_IMAGE_PATH = temporaryConfig.getNfs().getBlogImagePath();
			PRODUCT_IMAGE_PATH = temporaryConfig.getNfs().getProductImagePath();
			STORAGE_IMAGE_PATH = temporaryConfig.getNfs().getFileStorePath();
			USER_IMAGE_PATH = temporaryConfig.getNfs().getUserImagePath();
			MEDIA_IMAGE_PATH = temporaryConfig.getNfs().getMediaPath();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

    public TemporaryConfig getTemporaryConfig() {
		return temporaryConfig;
	}

}
