package Generator;

import Model.ConstCollection;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {

  synchronized static public void log(String temp) {
    String tempPath = ConstCollection.PATH + "/log.txt";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    try(FileWriter writer = new FileWriter(tempPath, true)){
      writer.write( "[" + dateFormat.format(date) + "] " + temp + "\r\n");
      writer.flush();
    } catch (IOException ex) {
      LogWriter.log(ex.getMessage());
    }
  }
}
