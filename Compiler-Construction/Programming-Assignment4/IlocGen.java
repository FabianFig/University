import java.util.ArrayList;
import java.util.List;

class IlocGen implements Emitter {


    // Emitter implementation
    private final List<String> out = new ArrayList<>();
    private List<String> currentParams = new ArrayList<>();

    @Override
    public void emit(String s) {
        out.add(s);
    }

    int labelCounter = 0 ; 

    @Override
    public String getLabel() {
        return "L"+labelCounter++ ; 
    }

    @Override
    public void setParams(List<String> params) {
        this.currentParams = params;
    }

    @Override
    public void emitLoadParam(String name, String reg) { 
        int index = currentParams.indexOf(name);
        if (index == -1) {
            throw new RuntimeException("Unknown parameter: " + name);
        }
        int offset = -(index + 1) * 8;
        emit(String.format("loadAI r0, %d => %s", offset, reg)) ; 
    }

    @Override
    public void pushReg(String reg) {
        emit("storeAI " + reg + " => r1, 0" + "         "  + "addI r1, ?WORD => r1");
    }

    @Override
    public void popTo(String reg) {
        emit("subI r1, ?WORD => r1" + "        " + "loadAI r1, 0 => " + reg);
    }

    // Generate method
    public List<String> generate(Program e) {
        out.clear();
        e.emit(this);
        return out;
    }
}
