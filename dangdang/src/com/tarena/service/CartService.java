package com.tarena.service;

import java.util.List;


/*
 * 购物车的业务接口
 */
public interface CartService {
	/*
	 * 将items中的数据，转换成一个类似 "3,8;2,10;4,5"(产品id,购买的数量)
	 * 这样的字符串
	 * 如果集合为空，返回"0".
	 */
	public String store();
	/*
	 * 依据content(类似 "3,8;2,10;4,5"(产品id,购买的数量)这样的字符串)
	 * 重新恢复cart中用户所购买的商品，即items集合
	 */
	public void load(String content);
	/*
	 * 根据产品pid添加到购物车中,成功返回true，失败返回false
	 */
	public boolean add(int pid);
	/*
	 * 更改产品购买的数量
	 */
	public void modify(int pid,int count);
	/*
	 * 把产品删除，放入到恢复列表中
	 */
	public void delete(int pid);
	/*
	 * 把恢复列表中的商品放入到已购买商品中
	 */
	public void recover(int pid);
	/*
	 * 购物车商品的总价格
	 */
	public double cost();
	/*
	 * 购物车商品的节省的价格
	 */
	public double retrenchCost();
	/*
	 * 显示购买了的产品
	 */
	public List<CartItem> buyList();
	/*
	 * 显示删除了的产品
	 */
	public List<CartItem> delList();
	/*
	 * 根据产品pid查找产品
	 */
	public CartItem findById(int pid);
	/*
	 * 清空产品
	 */
	public void clear();
	
	public List<CartItem> getItems();
}
