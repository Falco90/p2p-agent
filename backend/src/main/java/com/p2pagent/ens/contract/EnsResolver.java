package com.p2pagent.ens.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes4;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.8.0.
 */
@SuppressWarnings("rawtypes")
@Generated("org.web3j.codegen.SolidityFunctionWrapperGenerator")
public class EnsResolver extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_ABI = "ABI";

    public static final String FUNC_addr = "addr";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_CLEARRECORDS = "clearRecords";

    public static final String FUNC_CONTENTHASH = "contenthash";

    public static final String FUNC_DNSRECORD = "dnsRecord";

    public static final String FUNC_HASDNSRECORDS = "hasDNSRecords";

    public static final String FUNC_INTERFACEIMPLEMENTER = "interfaceImplementer";

    public static final String FUNC_ISAPPROVEDFOR = "isApprovedFor";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_MULTICALL = "multicall";

    public static final String FUNC_MULTICALLWITHNODECHECK = "multicallWithNodeCheck";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_PUBKEY = "pubkey";

    public static final String FUNC_RECORDVERSIONS = "recordVersions";

    public static final String FUNC_SETABI = "setABI";

    public static final String FUNC_setAddr = "setAddr";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SETCONTENTHASH = "setContenthash";

    public static final String FUNC_SETDNSRECORDS = "setDNSRecords";

    public static final String FUNC_SETINTERFACE = "setInterface";

    public static final String FUNC_SETNAME = "setName";

    public static final String FUNC_SETPUBKEY = "setPubkey";

    public static final String FUNC_SETTEXT = "setText";

    public static final String FUNC_SETZONEHASH = "setZonehash";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_TEXT = "text";

    public static final String FUNC_ZONEHASH = "zonehash";

    public static final Event ABICHANGED_EVENT = new Event("ABIChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event ADDRCHANGED_EVENT = new Event("AddrChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event ADDRESSCHANGED_EVENT = new Event("AddressChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event APPROVED_EVENT = new Event("Approved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>(true) {}));
    ;

    public static final Event CONTENTHASHCHANGED_EVENT = new Event("ContenthashChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event DNSRECORDCHANGED_EVENT = new Event("DNSRecordChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint16>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event DNSRECORDDELETED_EVENT = new Event("DNSRecordDeleted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint16>() {}));
    ;

    public static final Event DNSZONEHASHCHANGED_EVENT = new Event("DNSZonehashChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event INTERFACECHANGED_EVENT = new Event("InterfaceChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes4>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event NAMECHANGED_EVENT = new Event("NameChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event PUBKEYCHANGED_EVENT = new Event("PubkeyChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event TEXTCHANGED_EVENT = new Event("TextChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event VERSIONCHANGED_EVENT = new Event("VersionChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint64>() {}));
    ;

    @Deprecated
    protected EnsResolver(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EnsResolver(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EnsResolver(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EnsResolver(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ABIChangedEventResponse> getABIChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ABICHANGED_EVENT, transactionReceipt);
        ArrayList<ABIChangedEventResponse> responses = new ArrayList<ABIChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ABIChangedEventResponse typedResponse = new ABIChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.contentType = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ABIChangedEventResponse getABIChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ABICHANGED_EVENT, log);
        ABIChangedEventResponse typedResponse = new ABIChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.contentType = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<ABIChangedEventResponse> aBIChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getABIChangedEventFromLog(log));
    }

    public Flowable<ABIChangedEventResponse> aBIChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ABICHANGED_EVENT));
        return aBIChangedEventFlowable(filter);
    }

    public static List<AddrChangedEventResponse> getAddrChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ADDRCHANGED_EVENT, transactionReceipt);
        ArrayList<AddrChangedEventResponse> responses = new ArrayList<AddrChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddrChangedEventResponse typedResponse = new AddrChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.a = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AddrChangedEventResponse getAddrChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ADDRCHANGED_EVENT, log);
        AddrChangedEventResponse typedResponse = new AddrChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.a = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<AddrChangedEventResponse> addrChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAddrChangedEventFromLog(log));
    }

    public Flowable<AddrChangedEventResponse> addrChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDRCHANGED_EVENT));
        return addrChangedEventFlowable(filter);
    }

    public static List<AddressChangedEventResponse> getAddressChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ADDRESSCHANGED_EVENT, transactionReceipt);
        ArrayList<AddressChangedEventResponse> responses = new ArrayList<AddressChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddressChangedEventResponse typedResponse = new AddressChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.coinType = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AddressChangedEventResponse getAddressChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ADDRESSCHANGED_EVENT, log);
        AddressChangedEventResponse typedResponse = new AddressChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.coinType = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.newAddress = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<AddressChangedEventResponse> addressChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAddressChangedEventFromLog(log));
    }

    public Flowable<AddressChangedEventResponse> addressChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDRESSCHANGED_EVENT));
        return addressChangedEventFlowable(filter);
    }

    public static List<ApprovalForAllEventResponse> getApprovalForAllEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovalForAllEventResponse getApprovalForAllEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVALFORALL_EVENT, log);
        ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalForAllEventFromLog(log));
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public static List<ApprovedEventResponse> getApprovedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVED_EVENT, transactionReceipt);
        ArrayList<ApprovedEventResponse> responses = new ArrayList<ApprovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovedEventResponse typedResponse = new ApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.delegate = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovedEventResponse getApprovedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVED_EVENT, log);
        ApprovedEventResponse typedResponse = new ApprovedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.delegate = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.approved = (Boolean) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.owner = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovedEventResponse> approvedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovedEventFromLog(log));
    }

    public Flowable<ApprovedEventResponse> approvedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVED_EVENT));
        return approvedEventFlowable(filter);
    }

    public static List<ContenthashChangedEventResponse> getContenthashChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CONTENTHASHCHANGED_EVENT, transactionReceipt);
        ArrayList<ContenthashChangedEventResponse> responses = new ArrayList<ContenthashChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ContenthashChangedEventResponse typedResponse = new ContenthashChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.hash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ContenthashChangedEventResponse getContenthashChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CONTENTHASHCHANGED_EVENT, log);
        ContenthashChangedEventResponse typedResponse = new ContenthashChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.hash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ContenthashChangedEventResponse> contenthashChangedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getContenthashChangedEventFromLog(log));
    }

    public Flowable<ContenthashChangedEventResponse> contenthashChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONTENTHASHCHANGED_EVENT));
        return contenthashChangedEventFlowable(filter);
    }

    public static List<DNSRecordChangedEventResponse> getDNSRecordChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DNSRECORDCHANGED_EVENT, transactionReceipt);
        ArrayList<DNSRecordChangedEventResponse> responses = new ArrayList<DNSRecordChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DNSRecordChangedEventResponse typedResponse = new DNSRecordChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.resource = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.record = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DNSRecordChangedEventResponse getDNSRecordChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DNSRECORDCHANGED_EVENT, log);
        DNSRecordChangedEventResponse typedResponse = new DNSRecordChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.resource = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.record = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<DNSRecordChangedEventResponse> dNSRecordChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDNSRecordChangedEventFromLog(log));
    }

    public Flowable<DNSRecordChangedEventResponse> dNSRecordChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DNSRECORDCHANGED_EVENT));
        return dNSRecordChangedEventFlowable(filter);
    }

    public static List<DNSRecordDeletedEventResponse> getDNSRecordDeletedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DNSRECORDDELETED_EVENT, transactionReceipt);
        ArrayList<DNSRecordDeletedEventResponse> responses = new ArrayList<DNSRecordDeletedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DNSRecordDeletedEventResponse typedResponse = new DNSRecordDeletedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.resource = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DNSRecordDeletedEventResponse getDNSRecordDeletedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DNSRECORDDELETED_EVENT, log);
        DNSRecordDeletedEventResponse typedResponse = new DNSRecordDeletedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.name = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.resource = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DNSRecordDeletedEventResponse> dNSRecordDeletedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDNSRecordDeletedEventFromLog(log));
    }

    public Flowable<DNSRecordDeletedEventResponse> dNSRecordDeletedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DNSRECORDDELETED_EVENT));
        return dNSRecordDeletedEventFlowable(filter);
    }

    public static List<DNSZonehashChangedEventResponse> getDNSZonehashChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DNSZONEHASHCHANGED_EVENT, transactionReceipt);
        ArrayList<DNSZonehashChangedEventResponse> responses = new ArrayList<DNSZonehashChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DNSZonehashChangedEventResponse typedResponse = new DNSZonehashChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.lastzonehash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.zonehash = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DNSZonehashChangedEventResponse getDNSZonehashChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DNSZONEHASHCHANGED_EVENT, log);
        DNSZonehashChangedEventResponse typedResponse = new DNSZonehashChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.lastzonehash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.zonehash = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DNSZonehashChangedEventResponse> dNSZonehashChangedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDNSZonehashChangedEventFromLog(log));
    }

    public Flowable<DNSZonehashChangedEventResponse> dNSZonehashChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DNSZONEHASHCHANGED_EVENT));
        return dNSZonehashChangedEventFlowable(filter);
    }

    public static List<InterfaceChangedEventResponse> getInterfaceChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INTERFACECHANGED_EVENT, transactionReceipt);
        ArrayList<InterfaceChangedEventResponse> responses = new ArrayList<InterfaceChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InterfaceChangedEventResponse typedResponse = new InterfaceChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.interfaceID = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.implementer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static InterfaceChangedEventResponse getInterfaceChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(INTERFACECHANGED_EVENT, log);
        InterfaceChangedEventResponse typedResponse = new InterfaceChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.interfaceID = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.implementer = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<InterfaceChangedEventResponse> interfaceChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getInterfaceChangedEventFromLog(log));
    }

    public Flowable<InterfaceChangedEventResponse> interfaceChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INTERFACECHANGED_EVENT));
        return interfaceChangedEventFlowable(filter);
    }

    public static List<NameChangedEventResponse> getNameChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NAMECHANGED_EVENT, transactionReceipt);
        ArrayList<NameChangedEventResponse> responses = new ArrayList<NameChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NameChangedEventResponse typedResponse = new NameChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NameChangedEventResponse getNameChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NAMECHANGED_EVENT, log);
        NameChangedEventResponse typedResponse = new NameChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<NameChangedEventResponse> nameChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNameChangedEventFromLog(log));
    }

    public Flowable<NameChangedEventResponse> nameChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NAMECHANGED_EVENT));
        return nameChangedEventFlowable(filter);
    }

    public static List<PubkeyChangedEventResponse> getPubkeyChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PUBKEYCHANGED_EVENT, transactionReceipt);
        ArrayList<PubkeyChangedEventResponse> responses = new ArrayList<PubkeyChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PubkeyChangedEventResponse typedResponse = new PubkeyChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.x = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.y = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PubkeyChangedEventResponse getPubkeyChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PUBKEYCHANGED_EVENT, log);
        PubkeyChangedEventResponse typedResponse = new PubkeyChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.x = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.y = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PubkeyChangedEventResponse> pubkeyChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPubkeyChangedEventFromLog(log));
    }

    public Flowable<PubkeyChangedEventResponse> pubkeyChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUBKEYCHANGED_EVENT));
        return pubkeyChangedEventFlowable(filter);
    }

    public static List<TextChangedEventResponse> getTextChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TEXTCHANGED_EVENT, transactionReceipt);
        ArrayList<TextChangedEventResponse> responses = new ArrayList<TextChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TextChangedEventResponse typedResponse = new TextChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.indexedKey = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.key = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TextChangedEventResponse getTextChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TEXTCHANGED_EVENT, log);
        TextChangedEventResponse typedResponse = new TextChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.indexedKey = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.key = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.value = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<TextChangedEventResponse> textChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTextChangedEventFromLog(log));
    }

    public Flowable<TextChangedEventResponse> textChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TEXTCHANGED_EVENT));
        return textChangedEventFlowable(filter);
    }

    public static List<VersionChangedEventResponse> getVersionChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(VERSIONCHANGED_EVENT, transactionReceipt);
        ArrayList<VersionChangedEventResponse> responses = new ArrayList<VersionChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VersionChangedEventResponse typedResponse = new VersionChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newVersion = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static VersionChangedEventResponse getVersionChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(VERSIONCHANGED_EVENT, log);
        VersionChangedEventResponse typedResponse = new VersionChangedEventResponse();
        typedResponse.log = log;
        typedResponse.node = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newVersion = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<VersionChangedEventResponse> versionChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getVersionChangedEventFromLog(log));
    }

    public Flowable<VersionChangedEventResponse> versionChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VERSIONCHANGED_EVENT));
        return versionChangedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, byte[]>> ABI(byte[] node,
            BigInteger contentTypes) {
        final Function function = new Function(FUNC_ABI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Uint256(contentTypes)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, byte[]>>(function,
                new Callable<Tuple2<BigInteger, byte[]>>() {
                    @Override
                    public Tuple2<BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> addr(byte[] node) {
        final Function function = new Function(FUNC_addr, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> addr(byte[] node, BigInteger coinType) {
        final Function function = new Function(FUNC_addr, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Uint256(coinType)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(byte[] node, String delegate,
            Boolean approved) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.Address(160, delegate), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> clearRecords(byte[] node) {
        final Function function = new Function(
                FUNC_CLEARRECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> contenthash(byte[] node) {
        final Function function = new Function(FUNC_CONTENTHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> dnsRecord(byte[] node, byte[] name, BigInteger resource) {
        final Function function = new Function(FUNC_DNSRECORD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Bytes32(name), 
                new org.web3j.abi.datatypes.generated.Uint16(resource)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<Boolean> hasDNSRecords(byte[] node, byte[] name) {
        final Function function = new Function(FUNC_HASDNSRECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Bytes32(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> interfaceImplementer(byte[] node, byte[] interfaceID) {
        final Function function = new Function(FUNC_INTERFACEIMPLEMENTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Bytes4(interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedFor(String owner, byte[] node, String delegate) {
        final Function function = new Function(FUNC_ISAPPROVEDFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.Address(160, delegate)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String account, String operator) {
        final Function function = new Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> multicall(List<byte[]> data) {
        final Function function = new Function(
                FUNC_MULTICALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(data, org.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> multicallWithNodeCheck(byte[] nodehash,
            List<byte[]> data) {
        final Function function = new Function(
                FUNC_MULTICALLWITHNODECHECK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(nodehash), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.DynamicBytes>(
                        org.web3j.abi.datatypes.DynamicBytes.class,
                        org.web3j.abi.Utils.typeMap(data, org.web3j.abi.datatypes.DynamicBytes.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name(byte[] node) {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple2<byte[], byte[]>> pubkey(byte[] node) {
        final Function function = new Function(FUNC_PUBKEY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple2<byte[], byte[]>>(function,
                new Callable<Tuple2<byte[], byte[]>>() {
                    @Override
                    public Tuple2<byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], byte[]>(
                                (byte[]) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> recordVersions(byte[] param0) {
        final Function function = new Function(FUNC_RECORDVERSIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setABI(byte[] node, BigInteger contentType,
            byte[] data) {
        final Function function = new Function(
                FUNC_SETABI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Uint256(contentType), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAddr(byte[] node, BigInteger coinType,
            byte[] a) {
        final Function function = new Function(
                FUNC_setAddr, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Uint256(coinType), 
                new org.web3j.abi.datatypes.DynamicBytes(a)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAddr(byte[] node, String a) {
        final Function function = new Function(
                FUNC_setAddr, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.Address(160, a)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator,
            Boolean approved) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setContenthash(byte[] node, byte[] hash) {
        final Function function = new Function(
                FUNC_SETCONTENTHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.DynamicBytes(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDNSRecords(byte[] node, byte[] data) {
        final Function function = new Function(
                FUNC_SETDNSRECORDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setInterface(byte[] node, byte[] interfaceID,
            String implementer) {
        final Function function = new Function(
                FUNC_SETINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Bytes4(interfaceID), 
                new org.web3j.abi.datatypes.Address(160, implementer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setName(byte[] node, String newName) {
        final Function function = new Function(
                FUNC_SETNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.Utf8String(newName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPubkey(byte[] node, byte[] x, byte[] y) {
        final Function function = new Function(
                FUNC_SETPUBKEY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.generated.Bytes32(x), 
                new org.web3j.abi.datatypes.generated.Bytes32(y)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setText(byte[] node, String key, String value) {
        final Function function = new Function(
                FUNC_SETTEXT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.Utf8String(key), 
                new org.web3j.abi.datatypes.Utf8String(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setZonehash(byte[] node, byte[] hash) {
        final Function function = new Function(
                FUNC_SETZONEHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.DynamicBytes(hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceID) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> text(byte[] node, String key) {
        final Function function = new Function(FUNC_TEXT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node), 
                new org.web3j.abi.datatypes.Utf8String(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> zonehash(byte[] node) {
        final Function function = new Function(FUNC_ZONEHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(node)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    @Deprecated
    public static EnsResolver load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new EnsResolver(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EnsResolver load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EnsResolver(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EnsResolver load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new EnsResolver(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EnsResolver load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EnsResolver(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class ABIChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public BigInteger contentType;
    }

    public static class AddrChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public String a;
    }

    public static class AddressChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public BigInteger coinType;

        public byte[] newAddress;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class ApprovedEventResponse extends BaseEventResponse {
        public byte[] node;

        public String delegate;

        public Boolean approved;

        public String owner;
    }

    public static class ContenthashChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] hash;
    }

    public static class DNSRecordChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] name;

        public BigInteger resource;

        public byte[] record;
    }

    public static class DNSRecordDeletedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] name;

        public BigInteger resource;
    }

    public static class DNSZonehashChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] lastzonehash;

        public byte[] zonehash;
    }

    public static class InterfaceChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] interfaceID;

        public String implementer;
    }

    public static class NameChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public String name;
    }

    public static class PubkeyChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] x;

        public byte[] y;
    }

    public static class TextChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public byte[] indexedKey;

        public String key;

        public String value;
    }

    public static class VersionChangedEventResponse extends BaseEventResponse {
        public byte[] node;

        public BigInteger newVersion;
    }
}
