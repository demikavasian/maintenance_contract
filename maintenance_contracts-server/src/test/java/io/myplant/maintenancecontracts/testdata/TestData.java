package io.myplant.maintenancecontracts.testdata;

import io.myplant.maintenancecontact.api.model.*;
import io.myplant.maintenancecontact.api.model.enums.*;
import io.myplant.maintenancecontract.model.*;

import java.time.LocalDate;
import java.util.*;

public abstract class TestData {

    public static class ActivityTestData {
        public static Activity request() {
            final var activity = new Activity();
            activity.setActivityService(ActivityService.Analysis);
            activity.setActivityCategory(ActivityCategory.CHECK);
            activity.setActivityFrequency("1000");
            return activity;
        }

        public static ActivityEntity entity() {
            final var activity = new ActivityEntity();
            activity.setId(1L);
            activity.setActivityService(ActivityService.Analysis);
            activity.setActivityCategory(ActivityCategory.CHECK);
            activity.setActivityFrequency("1000");
            return activity;
        }
    }

    public static class EventTestData {
        public static Event request() {
            final var event = new Event();
            event.setEventService(EventService.Extended_Maintenance);
            event.setEventCategory(EventCategory.CON);
            event.setEventFrequency("1000");
            return event;
        }

        public static EventEntity entity() {
            final var event = new EventEntity();
            event.setId(1L);
            event.setEventService(EventService.Extended_Maintenance);
            event.setEventCategory(EventCategory.CON);
            event.setEventFrequency("1000");
            return event;
        }
    }

    public static class ComponentTestData {
        public static Component request() {
            final var component = new Component();
            component.setComponentNodeId(1315221L);
            component.setComponentName("TestName");
            component.setComponentQuantity("7");
            return component;
        }

        public static ComponentEntity entity() {
            final var component = new ComponentEntity();
            component.setComponentNodeId(1315221L);
            component.setComponentName("TestName");
            component.setComponentQuantity("7");
            return component;
        }
    }

    public static class ScopeTestData {
        public static List<Scope> request(int amount) {
            final var scopes = new ArrayList<Scope>();
            for (int i = 0; i < amount; i++) {
                final var scope = new Scope();
                int random = new Random().nextInt(3);
                if (random == 0) {
                    scope.setScopeType(ScopeType.ACTIVITY);
                    scope.setScopeValue(ActivityTestData.request());
                } else if (random == 1) {
                    scope.setScopeType(ScopeType.EVENT);
                    scope.setScopeValue(EventTestData.request());
                } else {
                    scope.setScopeType(ScopeType.COMPONENT);
                    scope.setScopeValue(ComponentTestData.request());
                }
                scope.setScopeCoverage(ScopeCoverage.CUSTOMER);
                scope.setAdditionalAttribute("test-attribute");
                scope.setAdditionalInfo("test-info");
                scopes.add(scope);
            }
            return scopes;
        }

        public static Set<ScopeEntity> entity(int amount) {
            final var scopes = new HashSet<ScopeEntity>();
            for (int i = 0; i < amount; i++) {
                final var scope = new ScopeEntity();
                scope.setId(1L);
                int random = new Random().nextInt(3);
                if (random == 0) {
                    scope.setScopeType(ScopeType.ACTIVITY);
                    scope.setActivityEntity(ActivityTestData.entity());
                } else if (random == 1) {
                    scope.setScopeType(ScopeType.EVENT);
                    scope.setEventEntity(EventTestData.entity());
                } else {
                    scope.setScopeType(ScopeType.COMPONENT);
                    scope.setComponentEntity(ComponentTestData.entity());
                }
                scope.setScopeCoverage(ScopeCoverage.CUSTOMER);
                scope.setAdditionalAttribute("test-attribute");
                scope.setAdditionalInfo("test-info");
                scopes.add(scope);
            }
            return scopes;
        }
    }

    public static class ClauseTestData {
        public static Clause request() {
            final var clause = new Clause();
            clause.setUnitEndCounter(1587);
            clause.setMinimumOphPerYear(8754941);
            clause.setMaximumOph("489985523");
            clause.setExpectedStartStopsPerYear(120);
            clause.setExtraWorkDiscountMaterial(54.22);
            clause.setExtraWorkDiscountLabor(78.11);
            clause.setReactionTimePersonnel("12");
            clause.setReactionTimeInitiate("10");
            clause.setTotalEfficiency("89");
            clause.setWarrantyStartDate(LocalDate.parse("2024-08-01"));
            clause.setWarrantyEndDate(LocalDate.parse("2025-08-01"));
            clause.setMaintenanceSection("TestSection");
            clause.setMaintenanceSchedule("TestSchedule");
            return clause;
        }

        public static ClauseEntity entity() {
            final var clause = new ClauseEntity();
            clause.setUnitEndCounter(1587);
            clause.setMinimumOphPerYear(8754941);
            clause.setMaximumOph("489985523");
            clause.setExpectedStartStopsPerYear(120);
            clause.setExtraWorkDiscountMaterial(54.22);
            clause.setExtraWorkDiscountLabor(78.11);
            clause.setReactionTimePersonnel("12");
            clause.setReactionTimeInitiate("10");
            clause.setTotalEfficiency("89");
            clause.setWarrantyStartDate(LocalDate.parse("2024-08-01"));
            clause.setWarrantyEndDate(LocalDate.parse("2025-08-01"));
            clause.setMaintenanceSection("TestSection");
            clause.setMaintenanceSchedule("TestSchedule");
            return clause;
        }
    }

    public static class GuaranteeTestData {
        public static List<Guarantee> request(int amount) {
            final var guarantees = new ArrayList<Guarantee>();
            for (int i = 0; i < amount; i++) {
                final var guarantee = new Guarantee();
                guarantee.setType("test-type");
                guarantee.setValue("test-value");
                guarantee.setUnitOfMeasure("test-unit");
                guarantees.add(guarantee);
            }
            return guarantees;
        }

        public static Set<GuaranteeEntity> entity(int amount) {
            final var guarantees = new HashSet<GuaranteeEntity>();
            for (int i = 0; i < amount; i++) {
                final var guarantee = new GuaranteeEntity();
                guarantee.setId(1L);
                guarantee.setType("test-type");
                guarantee.setValue("test-value");
                guarantee.setUnitOfMeasure("test-unit");
                guarantees.add(guarantee);
            }
            return guarantees;
        }
    }

    public static class AssetAdditionalScopeTestData {
        public static List<AssetAdditionalScope> request(int amount) {
            final var assetAdditionalScopes = new ArrayList<AssetAdditionalScope>();
            for (int i = 0; i < amount; i++) {
                final var assetAdditionalScope = new AssetAdditionalScope();
                assetAdditionalScope.setService("test-service");
                assetAdditionalScopes.add(assetAdditionalScope);
            }
            return assetAdditionalScopes;
        }
    }

    public static class ContractAdditionalScopeTestData {
        public static List<ContractAdditionalScope> request(int amount) {
            final var contractAdditionalScopes = new ArrayList<ContractAdditionalScope>();
            for (int i = 0; i < amount; i++) {
                final var contractAdditionalScope = new ContractAdditionalScope();
                contractAdditionalScope.setService("test-service");
                contractAdditionalScopes.add(contractAdditionalScope);
            }
            return contractAdditionalScopes;
        }
    }

    public static class AdditionalScopeEntityTestData {
        public static Set<AdditionalScopeEntity> entity(int amount) {
            final var additionalScopes = new HashSet<AdditionalScopeEntity>();
            for (int i = 0; i < amount; i++) {
                final var additionalScope = new AdditionalScopeEntity();
                additionalScope.setId(1L);
                additionalScope.setService("test-service");
                additionalScopes.add(additionalScope);
            }
            return additionalScopes;
        }
    }

    public static class AssetTestData {
        public static List<Asset> request(int amount) {
            final var assets = new ArrayList<Asset>();
            for (int i = 0; i < amount; i++) {
                final var asset = new Asset();
                asset.setId(1L);
                asset.setClause(ClauseTestData.request());
                asset.setGuarantees(GuaranteeTestData.request(2));
                asset.setScopes(ScopeTestData.request(3));
                asset.setAssetAdditionalScope(AssetAdditionalScopeTestData.request(2));
                assets.add(asset);
            }
            return assets;
        }

        public static Set<AssetEntity> entity(int amount) {
            final var assets = new HashSet<AssetEntity>();
            for (int i = 0; i < amount; i++) {
                final var asset = new AssetEntity();
                asset.setId(1L);
                asset.setClauseEntity(ClauseTestData.entity());
                GuaranteeTestData.entity(2).forEach(asset::addGuaranteeEntity);
                ScopeTestData.entity(3).forEach(asset::addScopeEntity);
                AdditionalScopeEntityTestData.entity(2).forEach(asset::addAdditionalScopeEntity);
                assets.add(asset);
            }
            return assets;
        }
    }

    public static class ContractTestData {
        public static Contract request() {
            final var contract = new Contract();
            contract.setId(1L);
            contract.setName("test-name");
            contract.setStatus(ContractStatus.IN_PROGRESS);
            contract.setOfferingType(OfferingType.FIRST_TIME_CONTRACT);
            contract.setEffectiveContractStartDate(LocalDate.parse("2024-08-01"));
            contract.setExclusiveEndDate(LocalDate.parse("2025-08-01"));
            contract.setAssets(AssetTestData.request(3));
            contract.setContractAdditionalScope(ContractAdditionalScopeTestData.request(2));
            return contract;
        }

        public static ContractEntity entity() {
            final var contract = new ContractEntity();
            contract.setId(1L);
            contract.setName("test-name");
            contract.setStatus(ContractStatus.IN_PROGRESS);
            contract.setOfferingType(OfferingType.FIRST_TIME_CONTRACT);
            contract.setEffectiveContractStartDate(LocalDate.parse("2024-08-01"));
            contract.setExclusiveEndDate(LocalDate.parse("2025-08-01"));
            AssetTestData.entity(3).forEach(contract::addAssetEntity);
            AdditionalScopeEntityTestData.entity(2).forEach(contract::addAdditionalScopeEntity);
            return contract;
        }
    }

}
