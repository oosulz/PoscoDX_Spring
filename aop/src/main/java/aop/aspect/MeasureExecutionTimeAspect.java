package aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..*.*.*(..))")
	public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
		// before

		StopWatch sw = new StopWatch();
		sw.start();

		// after
		Object result = pjp.proceed();
		sw.stop();
		long totalTime = sw.getTotalTimeMillis();
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		System.out.println("[Excution Time]["+ taskName + "] : " + totalTime);

		return result;
	}
}
