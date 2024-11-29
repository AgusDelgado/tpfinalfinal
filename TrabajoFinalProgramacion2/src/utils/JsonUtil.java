package utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Games;
import model.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
    public static void saveData(List<Users> users, List<Games> games, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DataWrapper dataWrapper = new DataWrapper(users, games);
        mapper.writeValue(new File(filePath), dataWrapper);
    }

    public static DataWrapper loadData(String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type dataType = new TypeToken<DataWrapper>() {}.getType();
            return gson.fromJson(reader, dataType);
        }
    }
}
