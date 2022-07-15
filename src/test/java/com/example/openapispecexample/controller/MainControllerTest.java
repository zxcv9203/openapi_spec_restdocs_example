package com.example.openapispecexample.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.epages.restdocs.apispec.SimpleType;
import com.example.openapispecexample.dto.MainRequest;
import com.example.openapispecexample.dto.MainRequest.Patch;
import com.example.openapispecexample.dto.MainRequest.Post;
import com.example.openapispecexample.dto.MainRequest.Put;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Get 테스트")
    void getTest() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.get("/")
            )
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("test-get",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .description("Get 요청 성공 여부 확인")
                    .responseFields(
                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지")
                    )
                    .responseSchema(
                        Schema.schema("MainResponse.Get")
                    )
                    .build())));

    }

    @Test
    @DisplayName("Post 테스트")
    void postTest() throws Exception {
        MainRequest.Post request = new Post("post request");
        mockMvc.perform(
                RestDocumentationRequestBuilders.post("/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
            )
            .andExpect(status().isCreated())
            .andDo(MockMvcRestDocumentationWrapper.document("test-post",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .description("Post 요청 성공 여부 확인")
                    .requestFields(
                        fieldWithPath("message").type(JsonFieldType.STRING).description("요청 메시지")
                    )
                    .responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("생성 ID")
                    )
                    .requestSchema(
                        Schema.schema("MainRequest.Post")
                    )
                    .responseSchema(
                        Schema.schema("MainResponse.Post")
                    )
                    .build())));
    }

    @Test
    @DisplayName("Put 테스트")
    void putTest() throws Exception {
        Long requestId = 1L;
        MainRequest.Put requestBody = new Put("put request");

        mockMvc.perform(
                RestDocumentationRequestBuilders.put("/{id}", requestId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(requestBody))
            )
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("test-put",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .description("Put 요청 성공 여부 확인")
                    .pathParameters(
                        parameterWithName("id").type(SimpleType.NUMBER).description("수정 ID")
                    )
                    .requestFields(
                        fieldWithPath("message").type(JsonFieldType.STRING).description("요청 메세지")
                    )
                    .responseFields(
                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                    )
                    .requestSchema(
                        Schema.schema("MainRequest.Put")
                    )
                    .responseSchema(
                        Schema.schema("MainResponse.Put")
                    )
                    .build())));
    }

    @Test
    @DisplayName("Patch 테스트")
    void patchTest() throws Exception {
        Long requestId = 1L;
        MainRequest.Patch requestBody = new Patch("patch request");

        mockMvc.perform(
                RestDocumentationRequestBuilders.patch("/{id}", requestId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(requestBody))
            )
            .andExpect(status().isOk())
            .andDo(MockMvcRestDocumentationWrapper.document("test-patch",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .description("Patch 요청 성공 여부 확인")
                    .pathParameters(
                        parameterWithName("id").type(SimpleType.NUMBER).description("수정 ID")
                    )
                    .requestFields(
                        fieldWithPath("message").type(JsonFieldType.STRING).description("요청 메세지")
                    )
                    .responseFields(
                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지")
                    )
                    .requestSchema(
                        Schema.schema("MainRequest.Patch")
                    )
                    .responseSchema(
                        Schema.schema("MainResponse.Patch")
                    )
                    .build())));
    }

    @Test
    @DisplayName("Delete 테스트")
    void deleteTest() throws Exception {
        Long request = 1L;

        mockMvc.perform(
                RestDocumentationRequestBuilders.delete("/{id}", request)
            )
            .andExpect(status().isNoContent())
            .andDo(MockMvcRestDocumentationWrapper.document("test-delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .description("Delete 요청 성공 여부 확인")
                    .pathParameters(
                        parameterWithName("id").type(SimpleType.NUMBER).description("삭제 ID")
                    )
                    .build())));
    }
}