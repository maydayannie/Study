package online.store.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
@Aspect
public class LoggingAspect {
	Logger logger = LogManager.getLogger(LoggingAspect.class);
	public Object around(ProceedingJoinPoint joinPoint) {
		logger.info("logBefore");
		Object target = joinPoint.getTarget();
		Object a = null;
		try {
			a = joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("logAfter------");
		return a;
	}
	public void before(JoinPoint joinPoint) {
		
		logger.info(joinPoint.getSignature().getName() + " is accessed");
	}
	
	public void after(JoinPoint joinPoint) {
		logger.info(joinPoint.getSignature().getName() + " is completed");
	}
	
	public void doTransaction(ProceedingJoinPoint joinPonit) {
		logger.info("Open a connection");
		
		try {
			joinPonit.proceed();
			logger.info("commit;");
		} catch (Throwable e) {
			logger.error("error",e);
			logger.info("rollback;");
		}		
	}	
}
