package com.bc.game.k3.service;

import com.bc.game.k3.domain.K3Num;
import com.bc.util.JsonCastUtil;
import com.bc.util.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("k3Service")
public class K3ServiceImpl implements IK3Service {

    public  List<K3Num> numList = new ArrayList<>();
   
    @Override
	public  List<K3Num> filterNum(List<K3Num> filterList,String jsonList){
    	
    	if(jsonList.equals("")){
    		return filterList;
    	}
    	
    	if(numList == null || numList.size() == 0){
    		initNumList();
        }
    	
    	if(filterList == null || filterList.size() == 0){
        	filterList = numList;
        }
    	
    	Long time1 = Calendar.getInstance().getTime().getTime();

        List<Map<String, Object>> list = JsonCastUtil.parseJSON2List(jsonList);

        

        int j = 0;
        
        for (Map<String, Object> map : list){
            Set<String> filterNameSet = new HashSet<String>();
            
            String[] filterNames = map.get("filterName").toString().split(",");
            CollectionUtils.addAll(filterNameSet, filterNames);
            
            filterList = getTypeList(filterList ,filterNameSet,map.get("filterType").toString());
            
            for (int i=0;i<filterList.size();i++){
                System.out.println(filterList.get(i).getOne() + "" + filterList.get(i).getTwo() + "" + filterList.get(i).getThree());
            }
            System.out.println("第"+ ++j + "次过滤结束");
        }
        System.out.println("用时:" + (Calendar.getInstance().getTime().getTime() - time1));
        return filterList;
    }
	/**
	 * 根据选择条件过滤投注号码
	 * @param numList 号码列表
	 * @param filterName 过滤名称
	 * @param filterType 过滤类型
	 * @return
	 */
    private List<K3Num> getTypeList(List<K3Num> numList,Set<String> filterName ,String filterType){
        List<K3Num> resultList = new ArrayList<K3Num>();
        
        for(int i = 0 ;i<numList.size();i++){
            String calFilterName = "";
            K3Num k3Num = numList.get(i);

            if(filterType.equals("0")){
                calFilterName = calculateType(k3Num);
            }else if(filterType.equals("1")){
                calFilterName = k3Num.getSum() + "";
            }else if(filterType.equals("2")){
                calFilterName = calculateSpacing(k3Num);
            }else if(filterType.equals("3")){
                calFilterName = calculateOddEven(k3Num);
            }else if(filterType.equals("4")){
                calFilterName = calculate012(k3Num);
            }else if(filterType.equals("5")){
                calFilterName = calculateBigMidSmall(k3Num);
            }

            if(filterName.contains(calFilterName)){
                resultList.add(numList.get(i));
            }
        }

        return resultList;
    }

	/**
	 * 计算形态
	 * @param k3Num
	 * @return
	 */
    @Override
	public  String calculateType(K3Num k3Num){
    	if(k3Num.getOne() - k3Num.getThree() == 0){
            return "三同号";
        } else if(k3Num.getTwo() - k3Num.getThree() == 0 || k3Num.getTwo() - k3Num.getOne() == 0){
            return "二同号";
        }else{
            if(k3Num.getThree() - k3Num.getTwo() == 1 && k3Num.getTwo() - k3Num.getOne() == 1){
                return "三连号";
            }
            return "三不同";
        }
    }

	/**
	 * 计算跨度
	 * @param
	 * @return
	 */
    @Override
	public   String calculateSpacing(K3Num k3Num){
        return (k3Num.getThree() - k3Num.getOne()) + "";
    }
	
    /**
	 * 计算奇偶
	 * 
	 * @param
	 * @return
	 */
    @Override
	public  String  calculateOddEven(K3Num k3Num){
        String result = "";
        int odd = 0;
        if(k3Num.getOne() % 2 == 1){
            ++ odd;
        }

        if(k3Num.getTwo() % 2 == 1){
            ++odd;
        }

        if(k3Num.getThree() % 2 == 1){
            ++odd;
        }

        switch(odd){
            case 0:
                result = "全偶";
                break;
            case 1:
                result = "两偶一奇";
                break;
            case 2:
                result = "两奇一偶";
                break;
            case 3:
                result = "全奇";
                break;
        }
        return result;
    }
	/**
	 * 计算012路 
	 * @param k3Num
	 * @return
	 */
    @Override
	public  String  calculate012(K3Num k3Num){
        StringBuilder sb =  new StringBuilder();
        List<Integer> list = new ArrayList<>();
        list.add(k3Num.getOne() % 3);
        list.add(k3Num.getTwo() % 3);
        list.add(k3Num.getThree() % 3);
        Collections.sort(list);
        for (Integer i : list){
            sb.append(i);
        }
        return sb.toString();
    }

    /**
	 * 计算大中小
	 * @param
	 * @return
	 */
    @Override
	public  String  calculateBigMidSmall(K3Num k3Num){
        StringBuilder sb =  new StringBuilder();
        List<Integer> list = new ArrayList<Integer>();
        
        list.add(k3Num.getOne());
        list.add(k3Num.getTwo());
        list.add(k3Num.getThree());
        
        for (Integer i : list){
            if(i <=2 ){
                sb.append("小") ;
            }else if(i <=4 ){
                sb.append("中") ;
            }else{
                sb.append("大") ;
            }
        }
        return sb.toString();
    }
    
    /**
     * 胆拖计算选择号码
     */
    @Override
	public  List<K3Num> K3CalDanTuoNum(List<String> dan,List<String> tuo){
    	
    	List<K3Num> k3NumList = new ArrayList<K3Num>();
    	
    	if(dan.size() == 0 && tuo.size() ==0){
    		throw new BusinessException("胆码与托码必须选择一个");
    	}
    	
    	if(dan != null && dan.size() != 0){
    		k3NumList = K3CalDanTuoNum(dan);
    	}
		
		
		//全托情况
		if(dan == null || dan.size() == 0){
			List<String> danList = new ArrayList<String>();
			for(int i=0;i<tuo.size();i++){//豹子
				danList.clear();
				danList.add(tuo.get(i));
				k3NumList.addAll(K3CalDanTuoNum(danList));
			}
			
			for(int i=0;i<tuo.size();i++){
				for(int j=i+1;j<tuo.size();j++){
					danList.clear();
					danList.add(tuo.get(i));
					danList.add(tuo.get(j));
					k3NumList.addAll(K3CalDanTuoNum(danList));
				}
			}
			
			for(int i=0;i<tuo.size();i++){
				for(int j=i+1;j<tuo.size();j++){
					for(int k=j+1;k<tuo.size();k++){
						K3Num k3Num = new K3Num(Integer.parseInt(tuo.get(i)),Integer.parseInt(tuo.get(j)),Integer.parseInt(tuo.get(k)));
						k3NumList.add(k3Num.sort());
					}
					
				}
			}
		}
		
		
		if(dan.size() == 1 && tuo.size()>=2){//两个及以上托码情况
			for(int i=0;i<tuo.size();i++){
				for(int j=i+1;j<tuo.size();j++){
					K3Num k3Num = new K3Num(Integer.parseInt(dan.get(0)),Integer.parseInt(tuo.get(i)),Integer.parseInt(tuo.get(j)));
					k3NumList.add(k3Num.sort());
				}
			}
			dan.add(dan.get(0));
		}else if(dan.size() == 1){
			dan.add(dan.get(0));
		}
		
		if(dan.size() == 2){
			for(int i=0;i<tuo.size();i++){
				K3Num k3Num = new K3Num(Integer.parseInt(dan.get(0)),Integer.parseInt(dan.get(1)),Integer.parseInt(tuo.get(i)));
				k3NumList.add(k3Num.sort());
			}
		}
		
		return k3NumList;
	}
	/**
	 * 全胆号码组合情况
	 */
	private List<K3Num> K3CalDanTuoNum(List<String> dan){
		
		List<K3Num> resultList = new ArrayList<K3Num>();
		Collections.sort(dan);
		if(dan.size() == 1){
			K3Num k3Num = new K3Num(Integer.parseInt(dan.get(0)),Integer.parseInt(dan.get(0)),Integer.parseInt(dan.get(0)));
			resultList.add(k3Num);
		}else if(dan.size() == 2){
			K3Num k3Num = new K3Num(Integer.parseInt(dan.get(0)),Integer.parseInt(dan.get(0)),Integer.parseInt(dan.get(1)));
			resultList.add(k3Num);
			
			K3Num k3Num1 = new K3Num(Integer.parseInt(dan.get(0)),Integer.parseInt(dan.get(1)),Integer.parseInt(dan.get(1)));
			resultList.add(k3Num1);
		}else{
			throw new BusinessException("胆码选择错误，不能小于1个大于3个");
		}
		return resultList;
	}
	/**
	 * 初始化快三号码
	 */
	 private  void initNumList(){
	        if(numList == null || numList.size() != 6*6*6 ){
	            for (int i=1 ;i<=6;i++){
	                for (int j=i ;j<=6;j++){
	                    for (int k=j ;k<=6;k++){
	                    	K3Num k3Num = new K3Num(i,j,k);
	                        numList.add(k3Num);
	                    }
	                }
	            }
	        }

	    }
	 
	 
	 public static void main(String args[]){
			IK3Service K3Service = new K3ServiceImpl();

			Long time1 = Calendar.getInstance().getTime().getTime();

	        K3Service.filterNum(null,"[{\"filterName\": \"两偶一奇,两奇一偶\", \"filterType\": \"3\"},{\"filterName\": \"15\", \"filterType\": \"1\"}]");
	        
	        System.out.println(Calendar.getInstance().getTime().getTime() - time1);
	    }
}
