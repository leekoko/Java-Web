package com.leekoko.tech;

import com.alibaba.fastjson.JSON;
import com.leekoko.pipeline.MemoryPipeline;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

public class TechPageProcesser implements PageProcessor {

    public static String TARGET_URL = "http://192.168.0.24:8091/home";
    public Site site = Site.me().setDomain("http://192.168.0.24:8091");

    public void startCrawl(){
        long startTime = System.currentTimeMillis();;
        MemoryPipeline pipeline = new MemoryPipeline();
        Spider.create(new TechPageProcesser()).addUrl(TARGET_URL).addPipeline(pipeline).thread(10).run();
        long endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }


    public void process(Page page) {
        //获取第一季度课程标题信息
        if (page.getUrl().regex(TARGET_URL).match()){
            //首页
            addUrl(page);
        }else{
            getHtml(page);
        }
    }

    public void addUrl(Page page) {
        Html html = page.getHtml();
        List<String> courseTitleList = html.xpath("//*[@id='tab']/tr/td[2]/a").all();
        ArrayList<String> urlList = getUrlList();
        page.addTargetRequests(urlList);
    }

    /**
     * 获取问题详情
     * @param page
     */
    public void getHtml(Page page) {
        Html html = page.getHtml();
        System.out.println(html.toString());
        String htmlStr = html.xpath("//*[@class=\"matters-content\"]").toString();
        System.out.println(htmlStr);
    }


    public Site getSite() {
        return site;
    }

    public ArrayList<String> getUrlList() {
        ArrayList<String> resultList = new ArrayList<String>();
        List<MenuPojo> menuPojoList = JSON.parseArray(json2, MenuPojo.class);
        int count = 0;
        for (int i = 0; i < menuPojoList.size(); i++) {
            MenuPojo menuPojo = menuPojoList.get(i);
            MenuDetailPojo url = menuPojo.getUrl().get(0);
            if(StringUtils.isBlank(url.getPath())){
                continue;
            }
            String urlStr = "http://192.168.0.24:8091" + url.getPath();
            resultList.add(urlStr);
        }
        return resultList;
    }

    public static String json2 = "[\n" +
            "                        {\n" +
            "                            title: \"高新技术企业落户\",\n" +
            "                            id: \"2-1\",\n" +
            "                            img: \"#icon-gaoxinjishuqiye\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineHighTechEnterprisesRewardGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技骨干人才\",\n" +
            "                            id: \"2-4\",\n" +
            "                            img: \"#icon-kejiguganrencai\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技创新小巨人\",\n" +
            "                            id: \"2-5\",\n" +
            "                            img: \"#icon-kejichuangxinxiaojuren\",\n" +
            "                            hidden: true,\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"企业入驻园区孵化申请\",\n" +
            "                            id: \"2-6\",\n" +
            "                            img: \"#icon-qiyeruzhuyuanqufuhuashenqing\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineDistrictEntpEnterIncubatorGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineDistrictEntpEnterIncubatorForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_INCU_DECD_PUT\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创客空间\",\n" +
            "                            id: \"2-7\",\n" +
            "                            img: \"#icon-chuangkekongjian\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineDistrictMakerSpaceGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineDistrictMakerSpaceForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HACK_DECD_PUT\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"加速器认定\",\n" +
            "                            id: \"2-8\",\n" +
            "                            img: \"#icon-jiasuqirending\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineDistrictAcceleratorGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineDistrictAcceleratorForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_ACCE_DECD_PUT\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"区孵化器试点\",\n" +
            "                            id: \"2-9\",\n" +
            "                            img: \"#icon-qufuhuaqishidian\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/pilotIncubator\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/incuPilotForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_INCU_PILOT\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"区创客空间试点\",\n" +
            "                            id: \"2-10\",\n" +
            "                            img: \"#icon-quchuangkekongjianshidian\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/pilotProject\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/hackPilotForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HACK_PILOT\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        }\n" +
            "                    ]";

    public static String json = "[{\n" +
            "                            title: \"配套资助项目\",\n" +
            "                            id: \"1-1\",\n" +
            "                            img: \"#icon-peitaozizhuxiangmu\",\n" +
            "                            hidden: true,\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    noProp: true,\n" +
            "                                    path: \"\", ///online/onlineDistrictServiceApplyGuide\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\", // /online/onlineDistrictServiceApply\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"自主立项项目\",\n" +
            "                            id: \"1-2\",\n" +
            "                            img: \"#icon-zizhulixiangxiangmu\",\n" +
            "                            hidden: true,\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技型中小企业贷款成本补贴（黄金10条）\",\n" +
            "                            id: \"1-3\",\n" +
            "                            img: \"#icon-kejixingqiyedaikuanchengbenbutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/onlineCntechEntpInfoGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineCntechEntpInfoForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_CNTECH_ENTP_INFO\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"瞪羚专项扶持资金\",\n" +
            "                            id: \"1-4\",\n" +
            "                            img: \"#icon-denglingfuchizhuanxiangzijin\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineSpecializedSupportGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineSpecializedSupportForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_DENL_ENTP_INFO\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"企业登记入库\",\n" +
            "                            id: \"1-5\",\n" +
            "                            img: \"#icon-qiyedengjiruku\",\n" +
            "                            hidden: true,\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技项目配套资助\",\n" +
            "                            id: \"1-6\",\n" +
            "                            img: \"#icon-kejixiangmupeitaozizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/scienceProjectFundingGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/scienceProjectFundingForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_PEI_ZI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"上级科技项目配套资助后补助\",\n" +
            "                            id: \"1-7\",\n" +
            "                            img: \"#icon-shangjikejijianglipeitaozizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/superiorSubsidyGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/houFengForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HOU_FENG\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"研发机构奖励资助（后补助）\",\n" +
            "                            id: \"1-8\",\n" +
            "                            img: \"#icon-yanfajigoujianglizizhuhoubuzhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/developSupportGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/houFengForm2/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HOU_FENG2\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"国家科技重大专项配套资助（后补助）\",\n" +
            "                            id: \"1-9\",\n" +
            "                            img:\n" +
            "                                \"#icon-guojiakejizhongdazhuanxiangpeitaozizhuhoubuzhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/specialSupportGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/houFengForm3/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HOU_FENG3\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"上级科技资金拨付\",\n" +
            "                            id: \"1-10\",\n" +
            "                            img: \"#icon-shangjikejizijinbofu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/fundsPayGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/keLiForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_KE_LI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"大学生科技企业场地租金补贴\",\n" +
            "                            id: \"1-11\",\n" +
            "                            img: \"#icon-daxueshengkejiqiyechangdizujinbutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/rentSubsidyForTechnologyEnterprises\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/changZuForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_CHANG_ZU\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"大学生科技企业创业项目资助\",\n" +
            "                            id: \"1-12\",\n" +
            "                            img: \"#icon-daxueshengkejiqiyechuangyexiangmutouzi\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/technologyEnterprises\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/collegeStudentTechEntrForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_COLLEGE_STUDENT_TECH_ENTR\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器接纳大学生科技企业补贴\",\n" +
            "                            id: \"1-13\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqijienadaxueshengkejiqiyebutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/enterpriseSubsidies\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/naTieForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_RECEIVE_STUDENT_SUBSIDY\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器留学人员企业场地租金补贴\",\n" +
            "                            id: \"1-14\",\n" +
            "                            img: \"#icon-daxueshengkejiqiyechangdizujinbutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/overseasStudents\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/liuZuForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_LIU_ZU\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"生物医药专业孵化器奖励\",\n" +
            "                            id: \"1-15\",\n" +
            "                            img: \"#icon-shengwuyiyaozhuanyefuhuaqijiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/biopharmaceuticalIncubatorAward\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/shengLiForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_SHENG_LI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器运营经费补贴\",\n" +
            "                            id: \"1-16\",\n" +
            "                            img: \"#icon-kejiqiyefuhuaqiyunyingjingfeibutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/businessIncubatorOperatingSubsidy\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/operateFundsForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_OPERATE_FUNDS\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title:\n" +
            "                                \"科技企业孵化器或投资机构投资在孵企业配套补贴\",\n" +
            "                            id: \"1-17\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqihuotouzijigoutouzizaifuqiyepeitaobutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/incubatedEnterpriseSubsidy\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/companyMatchingForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_COMPANY_MATCHING\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器毕业企业上市奖励\",\n" +
            "                            id: \"1-18\",\n" +
            "                            img: \"#icon-kejiqiyefuhuaqibiyeqiyeshangshijiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/listedCompaniesAward\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/fubiForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_FU_BI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器培育高新技术企业奖励\",\n" +
            "                            id: \"1-19\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqipeiyugaoxinjishuqiyejiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/highAndNewTechEnterprises\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/peiLiForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_PEI_LI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器承办重大创业服务活动资助\",\n" +
            "                            id: \"1-20\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqichengbanzhongdachuangyefuwuhuodongbutiezizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/majorEntrepreneurshipService\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/fuChuangForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_FU_CHUANG\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器开展创业环境研究资助\",\n" +
            "                            id: \"1-21\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqikaizhanchuangyehuanjingyanjiuzizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/fundingForEntrepreneurshipResearch\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/zhangYanForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_ZHANG_YAN\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"国家、省、市级科技企业孵化器认定奖励\",\n" +
            "                            id: \"1-22\",\n" +
            "                            img:\n" +
            "                                \"#icon-guojiashengshijikejiqiyefuhuaqirendingjiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/nationalProvincialAndMunicipalAwards\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/incuRecoForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_INCU_RECO_AWARD\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器中介服务机构租金补贴\",\n" +
            "                            id: \"1-23\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqizhongjiefuwujigouzujinbutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/rentSubsidyForIntermediaryServices\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/incuInterRentForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_INCU_INTER_RENT\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器聘任创业导师配套补贴\",\n" +
            "                            id: \"1-24\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqipinrenchuangyedaoshipeitaobutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/appointmentOfEntrepreneurshipTutors\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/entreMatchForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_INCU_ENTRE_MATCH\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器公共服务平台建设投资配套补贴\",\n" +
            "                            id: \"1-25\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqigonggongfuwupingtaijianshetouzipeitaobutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/publicServicePlatform\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/gongFuForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_GONG_FU\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技企业孵化器为在孵企业购买服务配套补贴\",\n" +
            "                            id: \"1-26\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejiqiyefuhuaqiweizaifuqiyegoumaifuwupeitaobutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/incubatingEnterprises\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/weiGouForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_WEI_GOU\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创客空间和孵化器培育瞪羚企业奖励\",\n" +
            "                            id: \"1-27\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangkekongjianhefuhuaqipeiyudenglingqiyejiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/customerSpaceAndIncubator\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/hackIncuForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HACK_INCU_DENGL\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创客空间考核资助\",\n" +
            "                            id: \"1-28\",\n" +
            "                            img: \"#icon-chuangkekongjiankaohezizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/customerSpaceExaminationFunding\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/hackAssessFundForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HACK_ASSESS_FUND\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创客空间内企业贷款贴息资助\",\n" +
            "                            id: \"1-29\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangkekongjianneiqiyedaikuantiexizizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/enterpriseLoansInChuangGuestSpace\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/hackEnterLoanForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HACK_ENTER_LOAN\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创客空间聘用创业导师资助\",\n" +
            "                            id: \"1-30\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangkekongjianpinyongchuangyedaoshizizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/hireEntrepreneurialTutorsToHelp\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/dsZuForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_DS_ZU\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创客空间租金补贴\",\n" +
            "                            id: \"1-31\",\n" +
            "                            img: \"#icon-chuangkekongjianzujinbutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/customerSpaceRentSubsidy\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/hackRentSubsForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HACK_RENT_SUBS\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创客团队生活住房房租补贴\",\n" +
            "                            id: \"1-32\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangketuanduishenghuozhufangfangzubutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/groupLivingHousingRent\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/houseZuForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_HOUSE_ZU\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title:\n" +
            "                                \"新建改造提升创客空间及引进国际创客实验室资助\",\n" +
            "                            id: \"1-34\",\n" +
            "                            img:\n" +
            "                                \"#icon-xinjiangaizaotishengchuangkekongjianjiyinjinguojichuangkeshiyanshizizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/internationalInnovationLaboratory\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/policyLtemForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_POLICY_LTEM_FORM\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"广州开发区国际科技合作项目\",\n" +
            "                            id: \"1-35\",\n" +
            "                            img: \"#icon-guangzhoukaifaquguojikejihezuoxiangmu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/internationalTenchnology\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path:\n" +
            "                                        \"/online/internationalTechCooperateForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_TECH_COOPERATE\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技保险专项\",\n" +
            "                            id: \"1-37\",\n" +
            "                            img: \"#icon-guangzhoukaifaqukejibaoxianzhuanxiang\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/scienceAndTechnologyInsuranceProgram\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/baoXianForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_BAO_XIAN\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title:\n" +
            "                                \"科技项目配套（科学技术奖和创新创业大赛奖励）\",\n" +
            "                            id: \"1-38\",\n" +
            "                            img:\n" +
            "                                \"#icon-kejixiangmupeitaokexuejishujianghechuangxinchuangyedasaijiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/scienceAndTechnologyProjects\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"IAB政策专项扶持\",\n" +
            "                            id: \"1-39\",\n" +
            "                            img: \"#icon-IABzhengcezhuanxiangfuchi\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/specialPolicySupport\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"通过广州市高新技术企业认定奖励\",\n" +
            "                            id: \"1-40\",\n" +
            "                            img:\n" +
            "                                \"#icon-tongguoguangzhoushigaoxinjishuqiyerendingjiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/recognitionOfHighAndNewTechnologyEnterprises\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/newBelieveForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_NEW_BELIEVE\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"科技创新小巨人补贴\",\n" +
            "                            id: \"1-41\",\n" +
            "                            img:\n" +
            "                                \"#icon-guangzhoushikejichuangxinxiaojurenbutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/smallGiantSubsidiesForTechnologicalInnovation\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/juBuForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_JU_BU\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"企业研发经费投入后补助\",\n" +
            "                            id: \"1-42\",\n" +
            "                            img:\n" +
            "                                \"#icon-guangzhoushiqiyeyanfajingfeitouruhoubuzhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/subsidiesAfterRDInvestment\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/qiYanForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_Qi_YAN\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"研究开发机构建设专项资金补贴\",\n" +
            "                            id: \"1-43\",\n" +
            "                            img:\n" +
            "                                \"#icon-guangzhoushiyanjiukaifajigoujianshezhuanxiangzijinbutie\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/researchAndDevelopmentInstitutionConstruction\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/jianSheForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_JIAN_SHE\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"生物产业研发奖励\",\n" +
            "                            id: \"1-44\",\n" +
            "                            img: \"#icon-shengwuchanyeyanfajiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/bioIndustryRDAward\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/bioIndustryRDAwardForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_SHENG_JIAN\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"区孵化器考核\",\n" +
            "                            id: \"1-45\",\n" +
            "                            img: \"#icon-qufuhuaqikaohe\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"区创客空间考核\",\n" +
            "                            id: \"1-46\",\n" +
            "                            img: \"#icon-quchuangkekongjiankaohe\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/districtCustomerSpaceAssessment\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/spaceCheckForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_SPACE_CHECK\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"高新技术企业落户奖励资金\",\n" +
            "                            id: \"1-47\",\n" +
            "                            img: \"#icon-gaoxinjishuqiyelahujianglizijin\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/newSettleGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/newSettleForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_NEW_SETTLE\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "\n" +
            "                        {\n" +
            "                            title: \"创业创新领军人才（科技领军人才）成长奖励\",\n" +
            "                            id: \"1-48\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangyechuangxinlingjunrencaikejilingjunrencaichengchangjiangli\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/leadingGrowthIncentive\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/renChengForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_REN_CHENG\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title:\n" +
            "                                \"创业创新领军人才（科技领军人才）启动资金及项目资助\",\n" +
            "                            id: \"1-49\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangyechuangxinlingjunrencaikejilingjunrencaiqidongzijinjixiangmuzizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/funding\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/fundingForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title:\n" +
            "                                \"创业创新领军人才 （科技领军人才）市场拓展资助\",\n" +
            "                            id: \"1-50\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangyechuangxinlingjunrencaikejilingjunrencaishichangtuozhanzizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/leadingMarktDeve\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/renShiForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_REN_SHI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创业领军人才 （科技领军人才）贷款贴息\",\n" +
            "                            id: \"1-51\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangyelingjunrencaikejilingjunrencaidaikuantiexi\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/leadingMoney\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/renDaiForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_REN_DAI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创业领军人才 （科技领军人才）场地补贴\",\n" +
            "                            id: \"1-52\",\n" +
            "                            img:\n" +
            "                                \"#icon-chuangyechuangjunrencaikejilingjunrencaichangdibuzhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/leadingTalentField\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/renDiForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_REN_DI\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"上级科技奖励配套资助\",\n" +
            "                            id: \"1-53\",\n" +
            "                            img: \"#icon-shangjikejijianglipeitaozizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path: \"/online/scienceAward\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/shangFengForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_SHANG_FENG\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"创业英才创业资助\",\n" +
            "                            id: \"2-3\",\n" +
            "                            img: \"#icon-chuangyeyingcai\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"/online/onlineEntrepreneurialTalentsGuide\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/entreTalentForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_ENTRE_TALENT\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        },\n" +
            "                        {\n" +
            "                            title: \"瞪羚企业申报\",\n" +
            "                            id: \"1-54\",\n" +
            "                            img: \"#icon-chuangyechuangxinlingjunrencaikejilingjunrencaiqidongzijinjixiangmuzizhu\",\n" +
            "                            url: [\n" +
            "                                {\n" +
            "                                    title: \"申请指南\",\n" +
            "                                    path:\n" +
            "                                        \"\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    title: \"立即申报\",\n" +
            "                                    path: \"/online/dlentpDeclForm/add\",\n" +
            "                                    noProp: true,\n" +
            "                                    type: \"page\",\n" +
            "                                    tabName: \"EDA_DLENTP_DECL_INFO\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        }]";


}
