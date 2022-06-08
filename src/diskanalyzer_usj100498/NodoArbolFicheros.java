package diskanalyzer_usj100498;

import java.util.ArrayList;
import java.util.List;

public class NodoArbolFicheros <Fichero>{
	
	    private Fichero data = null;
	    private List<NodoArbolFicheros> children = new ArrayList<>();
	    private NodoArbolFicheros parent = null;

	    public NodoArbolFicheros(Fichero data) {
	        this.data = data;
	    }

	    public void addChild(NodoArbolFicheros child) {
	        child.setParent(this);
	        this.children.add(child);
	    }

	    public void addChild(Fichero data) {
	        NodoArbolFicheros<Fichero> newChild = new NodoArbolFicheros<>(data);
	        this.addChild(newChild);
	    }

	    public void addChildren(List<NodoArbolFicheros> children) {
	        for(NodoArbolFicheros t : children) {
	            t.setParent(this);
	        }
	        this.children.addAll(children);
	    }

	    public List<NodoArbolFicheros> getChildren() {
	        return children;
	    }

	    public Fichero getData() {
	        return data;
	    }

	    public void setData(Fichero data) {
	        this.data = data;
	    }

	    private void setParent(NodoArbolFicheros parent) {
	        this.parent = parent;
	    }

	    public NodoArbolFicheros getParent() {
	        return parent;
	    }
	}
	