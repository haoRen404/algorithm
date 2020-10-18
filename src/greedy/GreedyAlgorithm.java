package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {

	public static void main(String[] args) {
		// 贪心算法
		//题目：有几个电台，每个电台都能覆盖几个地区，如何选择电台，让所有地区都被覆盖
		
		//创建广播电台集合
		HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
		//创建各个电台
		HashSet<String> hashSet1 = new HashSet<String>();
		hashSet1.add("北京");
		hashSet1.add("上海");
		hashSet1.add("天津");
		
		HashSet<String> hashSet2 = new HashSet<String>();
		hashSet2.add("广州");
		hashSet2.add("北京");
		hashSet2.add("深圳");
		
		HashSet<String> hashSet3 = new HashSet<String>();
		hashSet3.add("成都");
		hashSet3.add("上海");
		hashSet3.add("杭州");
		
		HashSet<String> hashSet4 = new HashSet<String>();
		hashSet4.add("上海");
		hashSet4.add("天津");
		
		HashSet<String> hashSet5 = new HashSet<String>();
		hashSet5.add("杭州");
		hashSet5.add("大连");
		
		//把电台加入到广播电台集合中
		broadcasts.put("K1", hashSet1);
		broadcasts.put("K2", hashSet2);
		broadcasts.put("K3", hashSet3);
		broadcasts.put("K4", hashSet4);
		broadcasts.put("K5", hashSet5);
		
		//所有未被覆盖的地区的集合，也可以通过遍历broadcasts集合来加入，这里手动加入了
		//如果某个电台覆盖了就会被删去
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.add("北京");
		allAreas.add("上海");
		allAreas.add("天津");
		allAreas.add("广州");
		allAreas.add("深圳");
		allAreas.add("成都");
		allAreas.add("杭州");
		allAreas.add("大连");
		
		//创建一个ArrayList，存放所选择的电台集合
		ArrayList<String> selects = new ArrayList<String>();
		
		//定义一个临时集合，存放遍历时某电台所覆盖的地区和还没有被覆盖的地区的交集
		HashSet<String> tempSet = new HashSet<String>();
		
		//定义一个maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的key值
		//也就是保存能够覆盖最多为覆盖地区的电台
		String maxKey = null;
		while(allAreas.size() != 0) {//如果allAreas为空，则说明所有电台都覆盖了
			//每次循环都要把maxKey置空
			maxKey = null;
			
			//遍历广播电台集合broadcasts，取出对应的key值
			for(String key : broadcasts.keySet()) {
				//每进行一次for循环都要把临时集合tempSet清空
				tempSet.clear();
				
				//取出当前key（电台）能够覆盖的地区
				HashSet<String> areas = broadcasts.get(key);
				//把取出来的地区存放到临时变量
				tempSet.addAll(areas);
				//取出tempSet和allAreas两个集合的交集,交集会赋给tempSet
				tempSet.retainAll(allAreas);
				
				//如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的未覆盖地区数量要多，
				//则更新maxKey指向数量更多的key
				//tempSet.size() > broadcasts.get(maxKey).size()体现出贪心的思想，如果更优就选择你
				if(tempSet.size() > 0 && 
						(maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
					maxKey = key;
				}
			}
			
			//如果maxKey!=null，则把找出的电台maxKey加入到slects,并且把该地区从所有未被覆盖的地区集合allAreas中删去
			//也就是贪心算法找出了一个电台
			if(maxKey != null) {
				selects.add(maxKey);
				//所有违背覆盖的地区集合allAreas中删去该地区
				allAreas.removeAll(broadcasts.get(maxKey));
			}
		}
		
		System.out.println("贪心算法得到的结果：" + selects);
		
	}

}
