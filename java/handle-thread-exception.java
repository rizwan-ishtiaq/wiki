List<Integer> execute(List<LoadFiles> loadFilesList){
		List<Future<Integer>> futures = new ArrayList<>();
		ExecutorService executorService = Executors.newFixedThreadPool(nthread);
		for(LoadFiles loadFiles: loadFilesList) {
			FileConverter converter = createConverter(loadFiles);
			futures.add(executorService.submit(converter));
		}
		executorService.shutdown();
		List<Integer> convertedCounts = new ArrayList<>();
		try {
			for(Future<Integer> future: futures) {
				convertedCounts.add(future.get());
			}
		} catch (CancellationException | InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (ExecutionException e) {
			Throwable t = e.getCause();
			if (t instanceof UniparcRuntimeException exception) {
				throw exception;
			} else if (t instanceof RuntimeException exception) {
				throw exception;
			} else {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return convertedCounts;
		
	}
