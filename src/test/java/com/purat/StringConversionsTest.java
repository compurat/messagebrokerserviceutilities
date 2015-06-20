package com.purat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(MockitoJUnitRunner.class)
public class StringConversionsTest {
    private static final String TEST_TEXT="Hello world";
    private static final String TEST_EMPTY= "";
    private static final String TEST_KANJI= "今日は";
    private static final String TEST_DIACRITICS= "à bas Boötes";


    @Mock
    private Part part;
    private StringConversions stringConversions;
    private InputStream inputStream;


    @Before
    public void init() {
        stringConversions = new StringConversions();
    }

    @Test
    public void withString() {
       Assert.assertTrue(convertFromPartToString(TEST_TEXT).equals(TEST_TEXT));
    }

    @Test(expected = NullPointerException.class)
    public void withNull() {
        Assert.assertTrue(convertFromPartToString(null).equals(null));
    }

    @Test
    public void withEmpty() {
        Assert.assertTrue(convertFromPartToString(TEST_EMPTY).equals(TEST_EMPTY));
    }

    @Test
    public void withKanji () {
        Assert.assertTrue(convertFromPartToString(TEST_KANJI).equals(TEST_KANJI));
    }

    @Test
    public void withDiacritics () {
        Assert.assertTrue(convertFromPartToString(TEST_DIACRITICS).equals(TEST_DIACRITICS));
    }

    private String convertFromPartToString(String testContent) {
        try {
            inputStream = new ByteArrayInputStream(testContent.getBytes());
            Mockito.when(part.getInputStream()).thenReturn(inputStream);
        } catch (IOException e) {
            Assert.fail();
            e.printStackTrace();
        }
        return stringConversions.convertPartToString(part);
    }
}