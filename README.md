# junit-mockito-sample

Junit Mockito 单元测试Sample

### 场景模拟

模拟的是一个计算某个用户投资某P2P理财产品，每个月定期的投入固定本金 X。
假设投资 N 个月, 每个月的固定利息是 Y。

- 第一个月: X
- 第二个月: (X * (1+y)) + X
- 第三个月：(((X * (1+y)) + X) * (1+y)) + X

### 函数说明

- compoundingCalc 预设利息，固定投资计算
- compoundingCalcWithUserId 浮动利息(根据用户)，固定投资计算
- compoundingCalcWithMagicUserId 浮动利息(根据用户 + Magic number)，固定投资计算

### 测试用例

- compoundingCalc 函数正确性
- compoundingCalcWithUserId Mock Rate服务
- compoundingCalcWithMagicUserId 验证Rate服务调用正确性