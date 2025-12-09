package dev.sivalabs.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.MockMvcBuilderCustomizer;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.web.client.ApiVersionInserter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import({MockMvcTestConfig.class})
@AutoConfigureMockMvc
class BookControllerMockMvcTests {
    @Autowired
    MockMvcTester mvc;

    @Test
    void shouldSearchBooksUsingDefaultVersion() {
        MvcTestResult testResult = mvc.get().uri("/api/books/search?q=007").exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson()
                .convertTo(Books.class)
                .satisfies(books -> {
                    assertThat(books.books()).hasSize(1);
                    assertThat(books.books().getFirst().getIsbn()).isEqualTo("978-0-14-028008-4");
                });
    }

    @Test
    void shouldSearchBooksUsingV1() {
        MvcTestResult testResult = mvc.get().uri("/api/books/search?q=007")
                .apiVersion("1.0")
                .exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson()
                .convertTo(Books.class)
                .satisfies(books -> {
                    assertThat(books.books()).hasSize(1);
                    assertThat(books.books().getFirst().getIsbn()).isEqualTo("978-0-14-028008-4");
                });

    }

    @Test
    void shouldSearchBooksUsingV2() {
        MvcTestResult testResult = mvc.get()
                .uri("/api/books/search?q=007")
                .apiVersion("2.0")
                .exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson()
                .convertTo(Books.class)
                .satisfies(books -> {
                    assertThat(books.books()).hasSize(2);
                    assertThat(books.books())
                            .extracting("isbn")
                            .containsExactlyInAnyOrder("978-0-14-028008-4", "978-0-14-028007-3");
                });
    }

    @Test
    void shouldSearchBooksUsingV2WithHeader() {
        MvcTestResult testResult = mvc.get()
                .uri("/api/books/search?q=007")
                .header("API-Version", "2.0")
                .exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson()
                .convertTo(Books.class)
                .satisfies(books -> {
                    assertThat(books.books()).hasSize(2);
                    assertThat(books.books())
                            .extracting("isbn")
                            .containsExactlyInAnyOrder("978-0-14-028008-4", "978-0-14-028007-3");
                });
    }




}