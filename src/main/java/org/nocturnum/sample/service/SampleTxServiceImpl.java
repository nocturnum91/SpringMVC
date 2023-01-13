package org.nocturnum.sample.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.nocturnum.sample.mapper.Sample1Mapper;
import org.nocturnum.sample.mapper.Sample2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

    /*
      전파(Propagation) 속성
      - PROPAGATION_MADATORY: 반드시 특정한 트랜잭션이 존재한 상태에서만 작업 가능
      - PROPAGATION_NESTED: 기존에 트랜잭션이 있는 경우, 포함되어서 실행
      - PROPAGATION_NEVER: 트랜잭션 상황하에 실행되면 예외 발생
      - PROPAGATION_NOT_SUPPORTED: 트랜잭션이 있는 경우엔 트랜잭션이 끝날 때까지 보류된 후 실행
      - PROPAGATION_REQUIRED: 트랜잭션이 있으면 그 상황에서 실행, 없으면 새로운 트랜잭션 실행(기본 설정)
      - PROPAGATION_REQUIRED_NEW: 대상은 자신만의 고유한 트랜잭션으로 실행
      - PROPAGATION_SUPPORTS: 트랜잭션을 필요로 하지 않으나, 트랜잭션 상황하에 있다면 포함되어서 실행

      격리(Isolation) 레벨
      - DEFAULT: DB 설정, 기본 격리 수준(기본 설정)
      - SERIALIZABLE: 가장 높은 격리, 성능 저하의 우려가 있음
      - READ_UNCOMMITED: 커밋되지 않은 데이터에 대한 읽기를 허용
      - READ_COMMITED: 커밋된 데이터에 대해 읽기 허용
      - REPEATEABLE_READ: 동일 필드에 대해 다중 접근 시 모두 동일한 결과를 보장

      Read-only 속성
      - true인 경우 insert, update, delete 실행 시 예외 발생, 기본 설정은 false

      Rollback-for-예외
      - 특정 예외가 발생 시 강제로 Rollback

      No-Rollback-for-예외
      - 특정 예외 발생 시에는 Rollback 처리되지 않음

      @Transactional 적용 순서
      - 메서드의 @Transactional 설정이 가장 우선
      - 클래스의 @Transactional 설정은 메서드보다 우선순위가 낮음
      - 인터페으스의 @Transactional 설정이 가장 낮은 우선순위
    */

    @Setter(onMethod_ = {@Autowired})
    private Sample1Mapper mapper1;

    @Setter(onMethod_ = {@Autowired})
    private Sample2Mapper mapper2;

    @Transactional
    @Override
    public void addData(String str) {
        log.info("Mapper1...");
        mapper1.insertCol1(str);

        log.info("Mapper2...");
        mapper2.insertCol2(str);

        log.info("END...");
    }

}
