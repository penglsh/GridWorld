package solution;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

	private Queue<JigsawNode> exploreList;  // 用以保存已发现但未访问的节点
    private Set<JigsawNode> visitedList;    // 用以保存已发现的节点
    private int countSteps;
    /**
     * 拼图构造函数
     */
    public Solution() {

    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
        
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     * @throws IOException 
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	visitedList = new HashSet<>(1000);
        exploreList = new LinkedList<JigsawNode>();
    	beginJNode = new JigsawNode(bNode);
        endJNode = new JigsawNode(eNode);
        currentJNode = null;
        countSteps = 0;
        
        visitedList.add(beginJNode);
        exploreList.add(beginJNode);
        
        while (!exploreList.isEmpty()) {
        	// (2-1)取出exploreList的第一个节点N，置为当前节点currentJNode
            //      若currentJNode为目标节点，则搜索成功，计算解路径，退出
            currentJNode = this.exploreList.poll();
            if (currentJNode.equals(eNode)) {
            	this.getPath();
            	this.printResult();
            	//printResult();
                return true;
            }
            countSteps ++;
            visitedList.add(currentJNode);
            
            for (int i = 0; i < 4; i++) {
            	JigsawNode tmpNode = new JigsawNode(currentJNode);
                if (tmpNode.move(i) && !this.visitedList.contains(tmpNode) && !exploreList.contains(tmpNode)) {
                    this.exploreList.add(tmpNode);
                }
            }
        }

        return false;
    }
    
    private void printResult() {
    	System.out.println("Jigsaw AStar Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        // System.out.println("Solution Path: ");
        // System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + countSteps);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
    }
    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        int geoDis = 0;
        int miss = getMiss(jNode);
        int Manhatondis = getDistance(jNode, geoDis);
        jNode.setEstimatedValue(1 * miss + 2 * Manhatondis + 1 * geoDis + 1 * s);
    }
    
    private int getMiss(JigsawNode node) {
    	int miss = 0;
    	int dimension = JigsawNode.getDimension();
    	int currState[] = node.getNodesState();
    	int targetState[] = endJNode.getNodesState();
        for (int index = 1; index < dimension * dimension; index++) {
            if (currState[index] != targetState[index]) {
                miss++;
            }
        } 
        return miss;
    }
    
    /*
     * get Manhaton distance and geometric distance
     */
    private int getDistance(JigsawNode node, int geoDis) {
    	int dis = 0;
    	int dim = JigsawNode.getDimension();
    	int nodeState[] = node.getNodesState();
    	int endState[] = endJNode.getNodesState();
    	
    	for (int i = 1; i <= dim * dim; i ++) {
    		for (int j = 0; j <= dim * dim; j ++) {
    			if (nodeState[i] == endState[j] && nodeState[i] != 0) {
    				int currRow = (int) (i - 1) / dim;
    	    		int currCol = (int) (i + 4) % dim;
    				int targetRow = (int) (j - 1) / dim;
    				int targetCol = (int) (j + 4) % dim;
    				dis += (Math.abs(currRow - targetRow) + Math.abs(currCol - targetCol));
    				geoDis += Math.sqrt(Math.pow(targetRow - currRow,  2) + 
    						Math.pow(targetCol - currCol, 2));
    				break;    				
    			}
    		}
    	}
    	return dis;
    }
}
