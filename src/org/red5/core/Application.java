package org.red5.core;

import java.util.ArrayList;
import java.util.List;
import org.red5.server.adapter.MultiThreadedApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.ISubscriberStream;
import com.tutor.dao.TutorLiveDao;


public class Application extends MultiThreadedApplicationAdapter {
	/*
	 * 一、推流
	 * 		1、appConnect
	 * 		2、join
	 * 		3、streamPublishStart
	 * 二、用户进入直播，播放
	 * 		1、appConnect
	 * 		2、join
	 * 		3、streamSubscriberStart
	 * 三、用户关闭直播
	 * 		1、disconnect
	 * 		2、leave
	 * 		3、streamSubscriberClose
	 * 四、停止推流
	 * 		1、streamBroadcastClose
	 * 		2、disconnect
	 * 		3、leave
	 * 
	 */
/*	@Autowired
	TutorLiveService tutorLiveService;*/
	
 
	@Override
	public boolean appConnect(IConnection arg0, Object[] arg1) {
		return super.appConnect(arg0, arg1);
	}
	
	@Override
	public boolean join(IClient arg0, IScope arg1) {
		return super.join(arg0, arg1);
	}
	
	@Override
	public void streamPublishStart(IBroadcastStream stream){
		TutorLiveDao tDao = new TutorLiveDao();
		int flag = 0;
		List<String> list = new ArrayList<String>();
		try {
			list = tDao.getRTMPkeyList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("********"+list.size());
		for(int i=0;i<list.size();i++){
			if(stream.getPublishedName().equals(list.get(i))){
				flag = 1;
			}
		}
		if(flag==0){
			Red5.getConnectionLocal().close();
		}
		/*if(stream.getPublishedName().equals("123456")){
			Red5.getConnectionLocal().close();
		}*/
	}
	
	@Override
	public void streamSubscriberStart(ISubscriberStream stream) {
		super.streamSubscriberStart(stream);
	}
	
	@Override
	public void disconnect(IConnection arg0, IScope arg1) {
		super.disconnect(arg0, arg1);
	}
	
	@Override
	public void leave(IClient arg0, IScope arg1) {
		super.leave(arg0, arg1);
	}
	
	@Override
	public void streamSubscriberClose(ISubscriberStream arg0) {
		super.streamSubscriberClose(arg0);
	}

	@Override
	public void streamBroadcastClose(IBroadcastStream arg0) {
		super.streamBroadcastClose(arg0);
	}
}