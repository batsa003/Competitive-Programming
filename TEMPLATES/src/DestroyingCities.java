/**
 * Created by Bat-Orgil on 8/20/2015.
 */
public class DestroyingCities {


    /* Why is min cut= destroying least number of cities?
    For a undirected graph, S,T are given. we want to remove least number of vertices, so that S and T are disconnected.
    Let's construct a new graph where for each node except S,T, there is u1 and u2.
    Add an edge from u1 to u2 with cap 1.
    For (u,v) edges, add edges (u1,v2,INF), (u2,v1,INF).
    Now if there is no directed edge between S,T ( which would make it impossible), why is minimum cut answer?

    First of all, if we choose all of the vertices u1, ... , un in set S, 
    *
    *
    *
    *
    *
    *
    *
    *
    * */
}
