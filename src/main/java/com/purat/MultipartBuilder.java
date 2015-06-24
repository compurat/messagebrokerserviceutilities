package com.purat;

import com.purat.annotation.ConnectionName;
import com.purat.annotation.FieldName;
import com.purat.constants.FieldNames;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by compurat on 23-6-15.
 */
@Component
public class MultipartBuilder<T> {

    public Map<String, AbstractContentBody> createParts(T bean) {

        Map<String, AbstractContentBody> parts = new HashMap<String, AbstractContentBody>();
        ConnectionName connectionName = bean.getClass().getDeclaredAnnotation(ConnectionName.class);
        try {
            if(connectionName != null) {
                String connectionNameValue = connectionName.connectionName();
                parts.put(FieldNames.connection.name() , new StringBody(connectionNameValue));
            }
            Field[] fields = bean.getClass().getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);
                String name  = null;
                if (fieldName != null) {
                    name = fieldName.fieldName();
                } else {
                    name = field.getName();
                }
                StringBody stringBody = new StringBody(String.valueOf(field.get(bean)));
                parts.put(name,stringBody);
                field.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return parts;
    }
}
