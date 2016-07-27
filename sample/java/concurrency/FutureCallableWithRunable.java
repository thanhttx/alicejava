public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService service = Executors.newFixedThreadPool(50);
		for(int i = 0; i <100;i++){
			
			//Complete all task -> System.out.println("complete");
			/*Future<Integer> future = service.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return 1;
				}
			});
			System.out.println(future.get());*/
			//Not complete all task -> System.out.println("complete");
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(1);
				}
			});
		}
		
		System.out.println("complete");
		service.shutdown();
	}
