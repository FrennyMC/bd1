package tec.bd.weather;

import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import tec.bd.weather.cli.MainCommand;
import picocli.CommandLine;
public class App 
        
{
    /*static{
        try (InputStream is = App.class.getClassLoader().getResourceAsStream(name: "logging.properties")){
            LogManager.getLogManager().readConfiguration(is);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static final Logger LOGGER =  Logger.getLogger(App.class.getName());*/
    public static void main( String[] args )
    {
        CommandLine cmd = new CommandLine(new MainCommand());
        cmd.setExecutionStrategy(new CommandLine.RunAll());
        cmd.execute(args);
        
        if (args.length == 0){
            cmd.usage(System.out);
        }
    }
}
