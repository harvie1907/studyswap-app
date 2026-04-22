package com.studyswap.app;

import android.content.Context;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.JSONObject;

public class FileStorageManager {
    private Context context;
    private File storageDir;

    public FileStorageManager(Context context) {
        this.context = context;
        // Storage location
        this.storageDir = new File(context.getFilesDir(), "studyswap_data");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
    }

    // Save user data (WRITE_EXTERNAL_STORAGE)
    public void saveUser(User user) throws Exception {
        File userFile = new File(storageDir, "users.json");
        JSONObject json = new JSONObject();
        json.put("username", user.getUsername());
        json.put("email", user.getEmail());
        json.put("password", user.getPassword());
        
        FileWriter writer = new FileWriter(userFile);
        writer.write(json.toString());
        writer.close();
    }

    // Read user data (READ_EXTERNAL_STORAGE)
    public User loadUser() throws Exception {
        File userFile = new File(storageDir, "users.json");
        FileReader reader = new FileReader(userFile);
        StringBuilder content = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            content.append((char) c);
        }
        reader.close();
        
        JSONObject json = new JSONObject(content.toString());
        User user = new User();
        user.setUsername(json.getString("username"));
        user.setEmail(json.getString("email"));
        user.setPassword(json.getString("password"));
        
        return user;
    }
}
