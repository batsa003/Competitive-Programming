

/*
*   d[1...N][1..N]
*
*   d[i,j]=INF if no edge exists, cost of the edge otherwise
*
*   for k=1...N
*       for i=1 ...N
*           for j=1...N
*              d[i,j]= min(d[i,j], d[i,k] + d[k,j]);
*
*   d[i,j]= Min distance from i to j
*
*
* */








public class FloydWarshall {
}
