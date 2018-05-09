//package com.jhmk.earlywaring.util;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.mongodb.*;
//import com.mongodb.bulk.BulkWriteResult;
//import com.mongodb.client.*;
//import com.mongodb.client.model.*;
//import com.mongodb.client.result.DeleteResult;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.bson.types.ObjectId;
//
//import java.util.*;
//
///**
// * MONGODB数据操作工具类
// */
//public class MongoUtils {
//	private static final Map<String, String> BasicDBObject = null;
//	private static MongoClient mongoClient;
//	private static String dbName;
//	private static String queryDBName;
//	private static final Logger logger = LogManager.getLogger(MongoUtils.class);
//
//	static {
//		if (mongoClient == null) {
//			MongoClientOptions.Builder options = new MongoClientOptions.Builder();
//			options.connectionsPerHost(100);// 连接池设置为300个连接,默认为100
//			options.connectTimeout(3000);// 连接超时，推荐>3000毫秒
//			options.maxWaitTime(300); //
//			options.socketTimeout(0);// 套接字超时时间，0无限制
//			// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
//			options.threadsAllowedToBlockForConnectionMultiplier(10);
//			options.writeConcern(WriteConcern.SAFE);
//			options.build();
//			MongoClientOptions myOptions = options.build();
//			try {
//				//数据库连接实例
//
//				List<ServerAddress> serverAddressList = new ArrayList<>();
//				ServerAddress address = new ServerAddress("192.168.8.20", 20000);
////				ServerAddress address = new ServerAddress("172.16.19.212",20000);
//				serverAddressList.add(address);
//				mongoClient = new MongoClient(address, myOptions);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static MongoDatabase getDB(String dbName) {
//		if (dbName != null && !"".equals(dbName)) {
//			MongoDatabase database = mongoClient.getDatabase(dbName);
//			return database;
//		}
//		return null;
//	}
//
//	public static MongoCollection<Document> getCollection(String dbName, String collName) {
//		if (null == collName || "".equals(collName)) {
//			return null;
//		}
//		if (null == dbName || "".equals(dbName)) {
//			return null;
//		}
//		MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
////		System.out.println(collection);
//		return collection;
//	}
//
//	public static List<String> getAllCollections(String dbName) {
//		MongoIterable<String> colls = getDB(dbName).listCollectionNames();
//		List<String> _list = new ArrayList<String>();
//		for (String s : colls) {
//			_list.add(s);
//		}
//		return _list;
//	}
//}