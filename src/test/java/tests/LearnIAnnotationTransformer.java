package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
1. Нельзя навесить на класс
2. Triggers before testng starts to collect test suite
3. Перед тем, как добавить метод в список */
public class LearnIAnnotationTransformer {

    @Test
    @Bug(1)
    public void useIAnnotationTransformer() {
        Assert.assertEquals(2 * 2, 5);
    }
}
