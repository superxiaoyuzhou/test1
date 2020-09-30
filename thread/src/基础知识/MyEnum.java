package 基础知识;

public enum MyEnum {
    AAA(StrategyEnum.NORMAL),
    BBB(StrategyEnum.HOLIDAY);

    private StrategyEnum strategy;

    MyEnum(StrategyEnum strategy){
        this.strategy = strategy;
    }

    /**
     * 工资计算策略
     */
    private enum StrategyEnum {
        /**
         * 正常工作日
         */
        NORMAL{
            @Override
            public double add(int base) {

                return base * A;
            }
        },
        /**
         * 节假日
         */
        HOLIDAY {
            @Override
            double add(int base) {
                return base * B;
            }
        },
        /**
         * 实习期
         */
        INTERNSHIP {
            @Override
            double add(int base) {
                return base * C;
            }
        };

        public final int A = 1;
        public final int B = 3;
        public final double C = 0.8;

        StrategyEnum() {
        }

        abstract double add(int c);
    }

    public static void main(String[] args) {
        double wages = MyEnum.AAA.strategy.add(10000);
    }
}
