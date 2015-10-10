public class Arco
{
    private int nó_origem, nó_destino, custo_arco; 
    
    public Arco(int origem, int destino, int custo)
    {
        nó_origem = origem;
        nó_destino = destino;
        custo_arco = custo;
    }

    public int getOrigem()
    {
        return nó_origem;
    }
    
    public int getDestino()
    {
        return nó_destino;
    }
    
    public int getCusto()
    {
        return custo_arco;
    }
    
    public String toString()
    {
        return ("c("+nó_origem+","+nó_destino+") = "+custo_arco+".");
    }
}
