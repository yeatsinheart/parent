package api.report.services;
import com.common.result.Result;

public interface TestService {
    Result<String> test(String test);
}
