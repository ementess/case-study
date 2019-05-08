import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class ApiTestCases {

    String targetUrl="https://fssfed.ge.com/fss/as/token.oauth2?grant_type=client_credentials&scope=openid%20profile&client_id=DTE_AVIATION_DEV&client_secret=NGN8zlWyPlu37C1UpU4ucW9h9U7SgD3guAZcOG1XVCelYx";

    @Test(priority = 1)
    public void testStatusCode(){
       post(targetUrl).then().statusCode(400);

    }

    @Test(priority = 2)
    public void testBody(){
        get("https://postman-echo.com/GET").then().assertThat()
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test(priority = 3)
    public void testHeader(){
        get("https://postman-echo.com/GET").then()
                .header("Content-Encoding", "gzip");
    }

}
