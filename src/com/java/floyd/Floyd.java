package com.java.floyd;

import java.util.ArrayList;
import java.util.List;
public class Floyd {

    private static int INF = Integer.MAX_VALUE;
    private int[][] dist;
    //����i �� j�����·�����ȣ���ֵ��i��j�ıߵ�Ȩ��
    private int[][] path;
    private List<Integer> result = new ArrayList<Integer>();

    public static void main(String[] args) {
        Floyd graph = new Floyd(5);
        int[][] matrix = {
                {INF, 30, INF, 10, 50},
                {INF, INF, 60, INF, INF},
                {INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, 30},
                {50, INF, 40, INF, INF},
        };
        int begin=0;
        int end=4;
        graph.findCheapestPath(begin,end,matrix);
        List<Integer> list=graph.result;
        System.out.println(begin+" to "+end+",the cheapest path is:");
        System.out.println(list.toString());
        System.out.println(graph.dist[begin][end]);
    }

    public  void findCheapestPath(int begin,int end,int[][] matrix){
        floyd(matrix);
        result.add(begin);
        findPath(begin,end);
        result.add(end);
    }

    public void findPath(int i,int j){
        // �ҵ�·�ɽڵ�
        int k=path[i][j];
        if(k==-1)
            return;
        // ��i��·�ɽڵ���еݹ�Ѱ���м�ڵ�
        findPath(i,k);
        result.add(k);
        // ��j��·�ɽڵ���еݹ�Ѱ���м�ڵ�
        findPath(k,j);
    }
    public  void floyd(int[][] matrix){
        int size=matrix.length;
        for(int i=0;i< size;i++){
            for(int j=0;j< size;j++){
                path[i][j]=-1;
                dist[i][j]=matrix[i][j];
            }
        }
        for(int k=0;k< size;k++){
            for(int i=0;i< size;i++){
                for(int j=0;j< size;j++){
                    if(dist[i][k]!=INF&&
                            dist[k][j]!=INF&&
                            dist[i][k]+dist[k][j]< dist[i][j]){
                        // ����i��j�����ľ���
                        dist[i][j]=dist[i][k]+dist[k][j];
                        // ����i��j������·����Ϣ
                        path[i][j]=k;
                    }
                }
            }
        }
    }

    public Floyd(int size){
        this.path=new int[size][size];
        this.dist=new int[size][size];
    }
}