// Actividad: Árbol Binario de Búsqueda

// Clase Nodo
class Nodo {
    int clave;
    Nodo izquierdo, derecho;

    public Nodo(int elemento) {
        clave = elemento;
        izquierdo = derecho = null;
    }
}

public class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() { raiz = null; }

    // INSERCIÓN
    public void insertar(int clave) {
        raiz = insertarRec(raiz, clave);
    }

    private Nodo insertarRec(Nodo raiz, int clave) {
        if (raiz == null) return new Nodo(clave);
        if (clave < raiz.clave)
            raiz.izquierdo = insertarRec(raiz.izquierdo, clave);
        else if (clave > raiz.clave)
            raiz.derecho = insertarRec(raiz.derecho, clave);
        return raiz;
    }

    // RECORRIDOS
    public void inorden() { inordenRec(raiz); }
    private void inordenRec(Nodo raiz) {
        if (raiz != null) {
            inordenRec(raiz.izquierdo);
            System.out.print(raiz.clave + " ");
            inordenRec(raiz.derecho);
        }
    }

    public void preorden() { preordenRec(raiz); }
    private void preordenRec(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.clave + " ");
            preordenRec(raiz.izquierdo);
            preordenRec(raiz.derecho);
        }
    }

    public void postorden() { postordenRec(raiz); }
    private void postordenRec(Nodo raiz) {
        if (raiz != null) {
            postordenRec(raiz.izquierdo);
            postordenRec(raiz.derecho);
            System.out.print(raiz.clave + " ");
        }
    }

    // BÚSQUEDA: ACTIVIDAD 1
    public boolean buscar(int clave) {
        return buscarRec(raiz, clave);
    }

    private boolean buscarRec(Nodo raiz, int clave) {
        // si llego a null es porque no está
        if (raiz == null) {
            return false;
        }
        // si es igual la encontré
        if (clave == raiz.clave) {
            return true;
        }
        // si es menor busco a la izquierda, si no a la derecha
        if (clave < raiz.clave) {
            return buscarRec(raiz.izquierdo, clave);
        } else {
            return buscarRec(raiz.derecho, clave);
        }
    }

    // ELIMINACIÓN: ACTIVIDAD 2
    public void eliminar(int clave) {
        raiz = eliminarRec(raiz, clave);
    }

    private Nodo eliminarRec(Nodo raiz, int clave) {
        // si no existe no hago nada
        if (raiz == null) {
            return null;
        }

        // primero busco el nodo
        if (clave < raiz.clave) {
            raiz.izquierdo = eliminarRec(raiz.izquierdo, clave);
        } else if (clave > raiz.clave) {
            raiz.derecho = eliminarRec(raiz.derecho, clave);
        } else {
            // lo encontré

            // caso 1 y 2: es hoja o tiene un solo hijo
            if (raiz.izquierdo == null) {
                return raiz.derecho;
            }
            if (raiz.derecho == null) {
                return raiz.izquierdo;
            }

            // caso 3: tiene dos hijos
            // pongo el menor del lado derecho en este nodo
            raiz.clave = minimoValor(raiz.derecho);
            // y borro ese valor de donde estaba
            raiz.derecho = eliminarRec(raiz.derecho, raiz.clave);
        }
        return raiz;
    }

    // ACTIVIDAD 3: método auxiliar
    // busca el menor valor de un subárbol (siempre a la izquierda)
    public int minimoValor(Nodo raiz) {
        Nodo actual = raiz;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.clave;
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.insertar(50); arbol.insertar(30); arbol.insertar(20);
        arbol.insertar(40); arbol.insertar(70); arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Inorden:"); arbol.inorden();
        System.out.println("\nPreorden:"); arbol.preorden();
        System.out.println("\nPostorden:"); arbol.postorden();

        int claveBuscada = 40;
        System.out.println("\n\nBÚSQUEDA");
        if (arbol.buscar(claveBuscada))
            System.out.println("La clave " + claveBuscada + " se encontró.");
        else
            System.out.println("La clave " + claveBuscada + " no se encontró.");

        claveBuscada = 90;
        if (arbol.buscar(claveBuscada))
            System.out.println("La clave " + claveBuscada + " se encontró.");
        else
            System.out.println("La clave " + claveBuscada + " no se encontró.");

        System.out.println("\nMÍNIMO");
        // el 70 es el hijo derecho de la raíz
        System.out.println("Mínimo del subárbol con raíz 70: " + arbol.minimoValor(arbol.raiz.derecho));
        System.out.println("Mínimo del árbol completo: " + arbol.minimoValor(arbol.raiz));

        System.out.println("\nELIMINACIÓN");
        int nodo = 20; arbol.eliminar(nodo);
        System.out.println("Después de eliminar " + nodo + " (hoja)"); arbol.inorden();
        nodo = 70; arbol.eliminar(nodo);
        System.out.println("\nDespués de eliminar " + nodo + " (dos hijos)"); arbol.inorden();
        nodo = 50; arbol.eliminar(nodo);
        System.out.println("\nDespués de eliminar " + nodo + " (raíz)"); arbol.inorden();
        nodo = 99; arbol.eliminar(nodo);
        System.out.println("\nDespués de eliminar " + nodo + " (no existe)"); arbol.inorden();
        System.out.println("\nNueva raíz: " + arbol.raiz.clave);
        System.out.println("Preorden final:"); arbol.preorden();
        System.out.println();
    }
}
