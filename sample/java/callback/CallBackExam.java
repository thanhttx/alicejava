package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallBackExam {

	public static void main(String[] args) {

		Caller a = new Caller();
		a.call(1, new CallBack() {

			@Override
			public void onSuccess(Object rs) {
				System.out.println(rs);
			}

			@Override
			public void onFault(Throwable rs) {
				rs.printStackTrace();
			}
		});

		a.asyncCall(1, new CallBack() {

			@Override
			public void onSuccess(Object rs) {
				System.out.println(rs);
			}

			@Override
			public void onFault(Throwable rs) {
				rs.printStackTrace();
			}
		});

		System.out.println("Finish!");
	}

	public static class Caller {

		static ExecutorService e = Executors.newFixedThreadPool(5);

		public void call(Object a, CallBack callBack) {

			if (a != null) {
				callBack.onSuccess("Call success!");
				return;
			}

			callBack.onFault(new Throwable());
		}

		public void asyncCall(Object a, CallBack callBack) {

			e.submit(new Callable<Object>() {
				@Override
				public Object call() throws Exception {

					if (a != null) {
						callBack.onSuccess("Async Call success!");
					} else {
						callBack.onFault(new Throwable());
					}

					//e.shutdown();
					return null;
				}
			});
		}
	}

	public static interface CallBack {

		void onSuccess(Object result);

		void onFault(Throwable error);
	}
}
