package com.purat;

import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by compurat on 20-6-15.
 */
@Service
public class ServiceConnector {

    private static final String UTF_8 = "UTF-8";
    private static final Logger LOGGER = Logger.getLogger(ServiceConnector.class);
    public String getServiceResponse(final String url, final  Map<String, AbstractContentBody> parts) {
        String response = null;
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost post = new HttpPost(url);
        MultipartEntity multipartEntity = createEntity(parts);
        post.setEntity(multipartEntity);
        try {
            response = EntityUtils.toString(client.execute(post).getEntity(), UTF_8);
        } catch (IOException e) {
            LOGGER.error("could not access service.");
            e.printStackTrace();
        }

        client.getConnectionManager().shutdown();
        return response;

    }


    private MultipartEntity createEntity(final Map<String, AbstractContentBody> parts) {
        MultipartEntity entity = new MultipartEntity( HttpMultipartMode.BROWSER_COMPATIBLE );
        Set<String> keys = parts.keySet();
        for(String key: keys) {
            entity.addPart(key, parts.get(key));
        }

        return entity;
    }
}
