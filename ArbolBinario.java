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

        // Caso base 1: llegamos a un subárbol vacío, la clave no existe

        if (raiz == null) return false;

        // Caso base 2: la clave coincide con el nodo actual

        if (clave == raiz.clave) return true;

        // Caso recursivo: se descarta la mitad del árbol que no puede contener la clave

        if (clave < raiz.clave)

            return buscarRec(raiz.izquierdo, clave);

        return buscarRec(raiz.derecho, clave);

    }


    // ELIMINACIÓN: ACTIVIDAD 2

    public void eliminar(int clave) {

        raiz = eliminarRec(raiz, clave);

    }


    private Nodo eliminarRec(Nodo raiz, int clave) {

        // La clave no existe: el subárbol queda igual

        if (raiz == null) return null;

        // 1) Localizar el nodo

        if (clave < raiz.clave) {

            raiz.izquierdo = eliminarRec(raiz.izquierdo, clave);

        } else if (clave > raiz.clave) {

            raiz.derecho = eliminarRec(raiz.derecho, clave);

        } else {

            // 2) Nodo encontrado

            // Caso hoja o un solo hijo: se devuelve el otro hijo (null si es hoja)

            if (raiz.izquierdo == null) return raiz.derecho;

            if (raiz.derecho == null) return raiz.izquierdo;

            // Caso dos hijos: se copia el sucesor inorden (mínimo del subárbol derecho)

            raiz.clave = minimoValor(raiz.derecho);

            // y se elimina el sucesor de su ubicación original

            raiz.derecho = eliminarRec(raiz.derecho, raiz.clave);

        }

        return raiz;

    }


    // ACTIVIDAD 3: método auxiliar

    // Encuentra el menor valor de un subárbol: siempre hacia la izquierda

    private int minimoValor(Nodo raiz) {

        Nodo actual = raiz;

        while (actual.izquierdo != null)

            actual = actual.izquierdo;

        return actual.clave;

    }


    // Versión pública para probar el mínimo de un subárbol cuya raíz es "clave"

    public Integer minimoDeSubarbol(int clave) {

        Nodo actual = raiz;

        while (actual != null && actual.clave != clave)

            actual = (clave < actual.clave) ? actual.izquierdo : actual.derecho;

        return (actual == null) ? null : minimoValor(actual);

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

        System.out.println("Mínimo del subárbol con raíz 70: " + arbol.minimoDeSubarbol(70));

        System.out.println("Mínimo del árbol completo: " + arbol.minimoDeSubarbol(50));


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
