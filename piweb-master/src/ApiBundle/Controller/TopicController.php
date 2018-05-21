<?php
/**
 * Created by PhpStorm.
 * User: Aymen
 * Date: 22/03/2018
 * Time: 13:03
 */

namespace ApiBundle\Controller;


use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use ForumBundle\Form\TopicType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\DomCrawler\Image;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use UtilisateurBundle\Entity\Commentaire;
use UtilisateurBundle\Entity\Topic;
use Ob\HighchartsBundle\Highcharts\Highchart;
use UtilisateurBundle\Entity\User;


class TopicController extends Controller
{
    
    public function getStackAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $topics = $em->getRepository(Topic::class)->findAll();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $jsonEncoder = $serializer->normalize($topics);
        return new JsonResponse($jsonEncoder);
    }

  


    public function mobileAddAction(Request $request){

        $em = $this->getDoctrine()->getManager();
        $topics = $em->getRepository(Topic::class)->findAll();
        $topic = new Topic();
        $user = $em->getRepository(User::class)->find($request->get('id'));
        $topic ->setSujet($request->get('sujet'));
        $topic ->setDescription($request->get('description'));
        $topic->setImageName($request->get('path'));
        $topic->setIdUser($user);
        $topic->setDate(new \DateTime('now'));
        $em->persist($topic);
        $em->flush();
        return new JsonResponse();



    }

    public function mobileDetailsAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $topics = $em->getRepository(Topic::class)->find($request->get('id'));
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $jsonEncoder = $serializer->normalize($topics);
        return new JsonResponse($jsonEncoder);
    }


    public function mobileSearchAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $topics = $em->getRepository(Topic::class)->findMobile($request->get('d'));
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $jsonEncoder = $serializer->normalize($topics);
        return new JsonResponse($jsonEncoder);
    }

    public function ImagesAction(Request $request)
    {
        $publicResourcesFolderPath = $this->get('kernel')->getRootDir() . '/../web/uploads/images/';
        $image = $request->query->get("img");

        // This should return the file located in /mySymfonyProject/web/public-resources/TextFile.txt
        // to being viewed in the Browser
        return new BinaryFileResponse($publicResourcesFolderPath.$image);
    }

    public function mesTopicsMobAction(Request $request)
    {
        $us = $this->getUser();
        $em = $this->getDoctrine()->getManager();
        $topics = $em->getRepository(Topic::class)->findByIdUser($request->get('id'));
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $jsonEncoder = $serializer->normalize($topics);
        return new JsonResponse($jsonEncoder);

    }


    public function userMobAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $user = $em->getRepository(Topic::class)->userMobile($request->get("idT"));
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $jsonEncoder = $serializer->normalize($user);
        return new JsonResponse($jsonEncoder);

    }

    public function delMobAction(Request $request){

        $em = $this->getDoctrine()->getManager();
        $id = $request->get("idT");
        $topic = $em->getRepository(Topic::class)->find($id);
        $em->remove($topic);
        $em->flush();
        return new JsonResponse();


    }

}



