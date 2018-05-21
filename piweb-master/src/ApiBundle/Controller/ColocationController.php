<?php
/**
 * Created by PhpStorm.
 * User: Karim
 * Date: 23-03-18
 * Time: 15:16
 */

namespace ApiBundle\Controller;


use ColocationBundle\Entity\Colocation;
use ColocationBundle\Form\ColocationType;



use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use UtilisateurBundle\Entity\FavorisColocation;
use UtilisateurBundle\Entity\User;

class ColocationController extends Controller
{

    public function afficheeAnnonceAction(){

        //créer une instance de notre entity  manager
        $em=$this->getDoctrine()->getManager();
        $Colocation=$em->getRepository("ColocationBundle:Colocation")->findAll();


        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function($object){
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($Colocation);
        return new JsonResponse($formatted);

    }



    public function mobileAddAction(Request $request){


        $Colocation=new Colocation();
        $Colocation->setAdresse($request->get('adr'));
        $Colocation->setDescription($request->get('desc'));
        $Colocation->setPlaceDispo($request->get('place'));
        $Colocation->setPrix($request->get('prix'));
        $Colocation->setSexe($request->get('sexe'));
        $Colocation->setTypeColocation($request->get('type'));
        $Colocation->setTypeMaison($request->get('typeM'));
        $Colocation->setPath($request->get('path'));

        $em = $this->getDoctrine()->getManager();
       $us=$em->getRepository(User::class)->find($request->get('idu'));
        $Colocation->setIdUser($us);
        $em->persist($Colocation);
        $em->flush();
        return new JsonResponse();
    }



    public function searchColocMobAction($adresse )
    {

        //créer une instance de notre entity  manager
        $em = $this->getDoctrine()->getManager();
        $Colocation = $em->getRepository("ColocationBundle:Colocation")->findcolocationMob($adresse);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function($object){
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($Colocation);
        return new JsonResponse($formatted);
    }
    public function supprimercolocationMobAction(Request $request){
        $id=$request->get("id");
        $em=$this->getDoctrine()->getManager();
        $Colocation=$em->getRepository("ColocationBundle:Colocation")->find($id);
        $em->remove($Colocation);
        $em->flush();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function($object){
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($Colocation);
        return new JsonResponse($formatted);
    }

    public function detailColocationAction(Request $request)
    {
        $id=$request->get("id");

        $em=$this->getDoctrine()->getManager();
        $Colocation=$em->getRepository("ColocationBundle:Colocation")->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function($object){
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($Colocation);
        return new JsonResponse($formatted);



    }


    public function ImagesAction(Request $request)
    {
        $publicResourcesFolderPath = $this->get('kernel')->getRootDir() . '/../web/uploads/images/';
        $image = $request->query->get("img");

        // This should return the file located in /mySymfonyProject/web/public-resources/TextFile.txt
        // to being viewed in the Browser
        return new BinaryFileResponse($publicResourcesFolderPath.$image);
    }
    public function mesColocAction(Request $request){

        //créer une instance de notre entity  manager

        $em=$this->getDoctrine()->getManager();
        $us=$this->getUser();

        $Colocation=$em->getRepository("ColocationBundle:Colocation")->findByIdUser($request->get('id'));

        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function($object){
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($Colocation);
        return new JsonResponse($formatted);
    }

    public function modifierColocationMobAction(Request $request){
        $id=$request->get("id");
        $em=$this->getDoctrine()->getManager();

        $Colocation=$em->getRepository("ColocationBundle:Colocation")
            ->find($id);
        $Colocation->setAdresse($request->get('adr'));
        $Colocation->setDescription($request->get('desc'));
        $Colocation->setPlaceDispo($request->get('place'));
        $Colocation->setPrix($request->get('prix'));
        $Colocation->setSexe($request->get('sexe'));
        $Colocation->setTypeColocation($request->get('type'));
        $Colocation->setTypeMaison($request->get('typeM'));
        $Colocation->setPath($request->get('path'));
        $us=$this->getUser();

        $em->persist($Colocation);
        $em->flush();
        return new JsonResponse();
    }
}