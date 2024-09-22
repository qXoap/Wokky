package dev.xoapp.wokky.factory;
import dev.xoapp.wokky.profile.Profile;
import java.util.HashMap;

public class ProfileFactory {

    private static final HashMap<String, Profile> profiles = new HashMap<>();

    public static void register(Profile profile) {
        profiles.put(profile.getName(), profile);
    }

    public static Profile get(String name) {
        return profiles.get(name);
    }

    public static void delete(String name) {
        profiles.remove(name);
    }

    public static HashMap<String, Profile> getProfiles() {
        return profiles;
    }

    public static void save() {
        profiles.forEach((key, profile) -> {
            profile.getData().setData(profile.serialize());
        });
    }
}
