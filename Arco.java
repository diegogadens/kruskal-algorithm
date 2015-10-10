public class Arco
{
    private int n�_origem, n�_destino, custo_arco; 
    
    public Arco(int origem, int destino, int custo)
    {
        n�_origem = origem;
        n�_destino = destino;
        custo_arco = custo;
    }

    public int getOrigem()
    {
        return n�_origem;
    }
    
    public int getDestino()
    {
        return n�_destino;
    }
    
    public int getCusto()
    {
        return custo_arco;
    }
    
    public String toString()
    {
        return ("c("+n�_origem+","+n�_destino+") = "+custo_arco+".");
    }
}
