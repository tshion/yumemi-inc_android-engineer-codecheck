package jp.co.yumemi.android.code_check

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition
import org.junit.runner.RunWith

/**
 * アーキテクチャに沿った実装がされているかを検証するユニットテスト
 */
@AnalyzeClasses(packages = ["jp.co.yumemi.android.code_check"])
@RunWith(ArchUnitRunner::class)
class ArchitectureUnitTest {

    @ArchTest
    fun atoms(classes: JavaClasses) {
        val rules = noClasses().that().resideInAPackage("..atoms..")
            .should().dependOnClassesThat().resideInAnyPackage(
                "..models..",
                "..molecules..",
                "..organisms..",
                "..pages..",
                "..templates..",
            )
        rules.check(classes)
    }

    @ArchTest
    fun molecules(classes: JavaClasses) {
        val rules = noClasses().that().resideInAPackage("..molecules..")
            .should().dependOnClassesThat().resideInAnyPackage(
                "..models..",
                "..organisms..",
                "..pages..",
                "..templates..",
            )
        rules.check(classes)
    }

    @ArchTest
    fun organisms(classes: JavaClasses) {
        val rules = noClasses().that().resideInAPackage("..organisms..")
            .should().dependOnClassesThat().resideInAnyPackage(
                "..models..",
                "..pages..",
                "..templates..",
            )
        rules.check(classes)
    }

    @ArchTest
    fun pages(classes: JavaClasses) {
        val rules = noClasses().that().resideInAPackage("..pages..")
            .should().dependOnClassesThat().resideInAnyPackage(
                "..models..",
            )
        rules.check(classes)
    }

    @ArchTest
    fun templates(classes: JavaClasses) {
        val rules = noClasses().that().resideInAPackage("..templates..")
            .should().dependOnClassesThat().resideInAnyPackage(
                "..models..",
                "..pages..",
            )
        rules.check(classes)
    }

    @ArchTest
    fun 循環参照していない(classes: JavaClasses) {
        val rules = SlicesRuleDefinition.slices()
            .matching("jp.co.yumemi.android.code_check.(*)..")
            .should()
            .beFreeOfCycles()
        rules.check(classes)
    }
}
