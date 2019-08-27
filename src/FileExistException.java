/**
 * Created by jangh on 2019-03-12.
 */
public class FileExistException extends Exception{
    public FileExistException(){
        super("Exception: There is already an existing file for that author. File will be renamed as BU, and older BUfiles will be deleted");
    }
    public FileExistException(String a){
        super(a);
    }
}
