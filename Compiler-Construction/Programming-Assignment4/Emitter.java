import java.util.List;

public interface Emitter {
    public void emit(String s) ;
    public void emitLoadParam(String name, String reg) ; // You'll need to modify this to satisfy Task #6
    public String getLabel() ;
    public void pushReg(String reg) ;
    public void popTo(String reg) ;
    public void setParams(List<String> params);
}
