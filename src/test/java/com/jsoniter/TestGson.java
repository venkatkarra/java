package com.jsoniter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jsoniter.annotation.GsonAnnotationSupport;
import com.jsoniter.output.JsonStream;
import junit.framework.TestCase;

public class TestGson extends TestCase {

    public void setUp() {
        GsonAnnotationSupport.enable();
    }

    public void tearDown() {
        GsonAnnotationSupport.disable();
    }

    public static class TestObject1 {
        @SerializedName("field-1")
        public String field1;
    }

    public void test_SerializedName() {
        Gson gson = new Gson();
        TestObject1 obj = gson.fromJson("{\"field-1\":\"hello\"}", TestObject1.class);
        assertEquals("hello", obj.field1);
        obj = JsonIterator.deserialize("{\"field-1\":\"hello\"}", TestObject1.class);
        assertEquals("hello", obj.field1);
    }

    public static class TestObject2 {
        @Expose(deserialize = false)
        public String field1;
    }

    public void test_Expose() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        TestObject2 obj = gson.fromJson("{\"field1\":\"hello\"}", TestObject2.class);
        assertNull(obj.field1);
        obj = JsonIterator.deserialize("{\"field1\":\"hello\"}", TestObject2.class);
        assertNull(obj.field1);
    }
}
