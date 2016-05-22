package com.josie.quake.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josie on 16/5/11.
 */
public class JacksonUtils {


    private final static ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
    private final static ObjectMapper FORMATED_MAPPER = new ObjectMapper();

    static {
//        FORMATED_MAPPER.configure(SerializationConfig., Boolean.TRUE);
    }

    /**
     * 调用get方法生成json字符串 <br>
     * <br>
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static String toJson(
            Object obj) {
        try {
            return DEFAULT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 拿到格式化之后的json <br>
     * <strong>仅用于测试</strong>
     * <br>
     *
     * @param obj
     * @return
     */
    public static String toFormatedJson(
            Object obj) {
        try {
            return FORMATED_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转换json为clazz. <br>
     * <strong>依赖get和set方法</strong> <br>
     * <br>
     *
     * @param jsonText
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T fromJson(
            String jsonText,
            Class<T> clazz) throws IOException {
        if (jsonText == null) {
            return null;
        }
        return DEFAULT_MAPPER.readValue(jsonText, clazz);
    }

    /**
     * 转换为集合类型的对象集合
     * <strong>依赖get和set方法</strong> <br>
     * <br>
     * <strong>example:</strong>
     *
     * <pre>
     * JacksonUtils.fromJson(jsonText, new TypeReference&lt;List&lt;FeedImage&gt;&gt;() {
     * });
     * </pre>
     *
     * @param jsonText
     * @param valueTypeRef
     *            org.codehaus.jackson.type.TypeReference
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(
            String jsonText,
            TypeReference<T> valueTypeRef) throws IOException {
        if (jsonText == null) {
            return null;
        }
        return DEFAULT_MAPPER.readValue(jsonText, valueTypeRef);
    }

    /**
     * 从json字符串中读取出指定的节点 <br>
     * <br>
     *
     * @param json
     * @param key
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static JsonNode getValueFromJson(
            String json,
            String key) throws JsonProcessingException, IOException {
        JsonNode node = DEFAULT_MAPPER.readTree(json);
        return node.get(key);
    }

    /**
     * 把json生成jsonNode <br>
     * <br>
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static JsonNode getJsonNode(
            String json) throws JsonProcessingException, IOException {
        return DEFAULT_MAPPER.readTree(json);
    }

    /**
     * 从json字符串中读取数组节点所包含的JsonNode List<br>
     * <br>
     *
     * @param json
     * @param key
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static List<JsonNode> getListFromJson(
            String json,
            String key) throws JsonProcessingException, IOException {
        List<JsonNode> jsonNodes = null;
        JsonNode node = DEFAULT_MAPPER.readTree(json);
        JsonNode arrayNode = node.get(key);
        if (arrayNode.isArray()) {
            jsonNodes = new ArrayList<JsonNode>();
            for (JsonNode jsonNode : arrayNode) {
                jsonNodes.add(jsonNode);
            }
        }
        return jsonNodes;
    }

    /**
     * 处理node为null的问题 <br>
     * <br>
     *
     * @param node
     * @return
     */
    public static String getStringValueFromNode(
            JsonNode node) {
        if (node != null) {
            return node.asText();
        }
        return null;
    }

}
