package com.gafapay.elasticsearch.kafka.constant;

public interface Constants {
   String KAFKA_TOPIC_ELASTIC_SEARCH_ACTIVITY_LOGS = "dig_topic_elastic_search_activity_logs";
   String KAFKA_TOPIC_ELASTIC_SEARCH_TRANSACTION_LOGS = "dig_topic_elastic_search_transaction_logs";
   String KAFKA_TOPIC_TXN_ENTRY_UPDATE_ELASTIC_SEARCH = "dig_topic_txn_entry_update_in_elastic_search";
   String KAFKA_GROUP_ELASTIC_SEARCH_DOCKER = "dig_elastic_search_docker_group";
   String KAFKA_TOPIC_COMMON_API_LOGS = "dig_topic_common_api_logs";
   String KAFKA_TOPIC_SEND_REPORT = "dig_topic_elastic_search_send_report";
   String KAFKA_TOPIC_REMOVE_TOKEN_FROM_REDIS_AUTH_TABLE = "dig_topic_remove_token";
   String KAFKA_TOPIC_CHANGE_STREAM_LOGS = "dig_topic_audit_logs_listen";
   String KAFKA_TOPIC_MAKE_ENTRY_IN_ES_TXN_USER_WALLET = "dig_topic_make_entry_in_es_txn_user_wallet";
   String KAFKA_TOPIC_NODAL_BANK_TRANSACTION_IN_ELASTICSEARCH = "dig_topic_nodal_bank_transaction_in_elasticsearch";
   String KAFKA_TOPIC_SEND_DATA_TO_DATADOG = "dig_topic_send_logs_to_data_dog_analytics";
   String KAFKA_TOPIC_SEND_REVENUE_CALCULATION_ON_TXN_CHARGES_TO_ANALYTICS = "dig_topic_send_revenue_calculation_on_txn_charges_to_analytics";
   String KAFKA_TOPIC_SEND_DELETE_AUDIT_LOGS_TO_DATADOG = "dig_topic_send_delete_audit_logs_to_datadog";
   String KAFKA_TOPIC_SEND_NOTIFICATION = "dig_topic_send_notification";
}
