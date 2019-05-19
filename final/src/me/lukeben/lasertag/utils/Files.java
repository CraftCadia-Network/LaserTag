package me.lukeben.lasertag.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Files {

    static Files instance = new Files();
    public static Files getInstance(){
        return instance;
    }

    FileConfiguration arenas;
    File afile;

    FileConfiguration lazertag;
    File lfile;

    public void setup(Plugin p){
        if(!p.getDataFolder().exists()){
            p.getDataFolder().mkdir();
        }
        afile = new File(p.getDataFolder(), "arenas.yml");
        if(!afile.exists()){
            try{
                File en = new File(p.getDataFolder(), "/arenas.yml");
                InputStream e = getClass().getResourceAsStream("/arenas.yml");
                copyFile(e, en);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        arenas = YamlConfiguration.loadConfiguration(afile);

        lfile = new File(p.getDataFolder(), "lasertag.yml");
        if(!lfile.exists()){
            try{
                File en = new File(p.getDataFolder(), "/lasertag.yml");
                InputStream e = getClass().getResourceAsStream("/lasertag.yml");
                copyFile(e, en);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        lazertag = YamlConfiguration.loadConfiguration(lfile);
    }

    public FileConfiguration getArenas(){
        return arenas;
    }

    public FileConfiguration getLazertag(){
        return lazertag;
    }

    public void saveArenas(){
        try {
            getArenas().save(this.afile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveLazertag(){
        try{
            getLazertag().save(this.lfile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void reloadArenas(){
        arenas = YamlConfiguration.loadConfiguration(afile);
    }

    public void reloadLazertag(){
        lazertag = YamlConfiguration.loadConfiguration(lfile);
    }

    public void copyFile(InputStream in, File out) throws Exception{

        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try{
            byte[] buf = new byte[1024];
            int i = 0;
            while((i = fis.read(buf)) != -1){
                fos.write(buf, 0, i);
            }
        }catch (Exception e){
            throw e;
        }finally {
            if(fis != null){
                fis.close();
            }
            if(fis != null){
                fos.close();
            }
        }

    }

}
