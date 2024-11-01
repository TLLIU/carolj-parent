package idv.tlliu.springboot.carol.model.entity.generator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.generator.EventType;

@IdGeneratorType(UniqueSequenceGenerator.class)
@Retention(RUNTIME) @Target({METHOD,FIELD})
public @interface UniqueSequenceId {
  String name();
  int startWith() default 1;
  int incrementBy() default 1;
  EventType[] event() default {EventType.INSERT, EventType.UPDATE};
}
