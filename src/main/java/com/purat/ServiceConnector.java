package com.purat;

import org.apache.http.entity.mime.content.AbstractContentBody;

import java.util.Map;

/**
 * Created by compurat on 22-6-15.
 */
public interface ServiceConnector {

    public String getServiceResponse(final String url, final Map<String, AbstractContentBody> parts);
}
