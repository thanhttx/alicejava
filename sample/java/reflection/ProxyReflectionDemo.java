package com.ttx.java.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author thanh
 *
 */
public class ProxyReflectionDemo {

	public static void main(String[] args) throws Exception {

		Object obj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { A.class },
				new MyInvoke(new AImpl()));
		System.out.println(obj.getClass());
		A a = (A) obj;
		a.print("vl");
		Class<?> clazz = Class.forName("com.sun.proxy.$Proxy0");
		
		//A is super of clazz [?]
		System.out.println(A.class.isAssignableFrom(clazz));
	}

	public static class MyInvoke implements InvocationHandler {

		Object object;

		public MyInvoke(Object object) {
			this.object = object;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			proxy = object;
			return proxy.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes()).invoke(proxy, args);
		}

	}

	public static interface A {
		void print(String x);
	}

	public static class AImpl {
		public void print(String x) {
			System.out.println(x);
		}
	}
}
